package ru.nsu.valikov.workers.chef;

import java.util.Map;
import java.util.MissingFormatArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.valikov.orders.models.Pizza;
import ru.nsu.valikov.orders.services.pop.Poper;
import ru.nsu.valikov.orders.services.push.Pusher;
import ru.nsu.valikov.utils.LoggerHelper;
import ru.nsu.valikov.workers.chef.services.Cook;
import ru.nsu.valikov.workers.chef.services.CookService;

/**
 * Cooker, takes an order from the queue -> cooks it -> sends it to the stock.
 */
public class Chef extends Thread {

    private boolean running;
    private final Poper orders;
    private final Pusher stock;
    private final int workEfficiency;
    private static final int PIZZA_LOWER_ID = 0;
    private static final int PIZZA_HIGHER_ID = 9;
    private static final Logger logger = LogManager.getLogger(Chef.class.getName());
    private static final Map<Integer, Integer> PIZZAZ_POOL = Map.of(0, 25, 1, 30, 2, 35, 3, 40, 4,
                                                                    45, 5, 50, 6, 55, 7, 17, 8, 23,
                                                                    9, 34);

    /**
     * Default constructor.
     *
     * @param workEfficiency ECE of worker
     * @param stock          pizzeria stock
     * @param orders         queue
     */
    public Chef(int workEfficiency, Pusher stock, Poper orders) {
        this.workEfficiency = workEfficiency;
        this.orders = orders;
        this.stock = stock;
        running = true;
    }


    @Override
    public void run() {
        while (running) {
            try {
                Pizza pizza = (Pizza) orders.pop();
                final int pizzaId = pizza.getPizzaId();
                if (pizzaId < PIZZA_LOWER_ID || pizzaId > PIZZA_HIGHER_ID) {
                    throw new MissingFormatArgumentException("Wrong pizzaId");
                }
                final int orderId = pizza.getOrderId();
                final String loggerMessage = LoggerHelper.messageWithOrderId(orderId);
                Cook cookService = new CookService(workEfficiency, PIZZAZ_POOL.get(pizzaId));
                logger.info(loggerMessage + "is cooking.");
                cookService.cook();
                logger.info(loggerMessage + "has cooked.");
                stock.push(pizza);
                logger.info(loggerMessage + "is waiting for delivering");
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }
}