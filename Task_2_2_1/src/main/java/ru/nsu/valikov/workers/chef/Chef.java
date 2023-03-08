package ru.nsu.valikov.workers.chef;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import ru.nsu.valikov.orders.pizza.Pizza;
import ru.nsu.valikov.orders.services.pop.Poper;
import ru.nsu.valikov.orders.services.push.Pusher;
import ru.nsu.valikov.utils.Logger;
import ru.nsu.valikov.utils.OrderStatus;
import ru.nsu.valikov.workers.chef.services.Cook;
import ru.nsu.valikov.workers.chef.services.CookService;

/**
 * Cooker, takes an order from the queue -> cooks it -> sends it to the stock.
 */
public class Chef extends Thread {
    private final Poper orders;
    private final Pusher stock;
    private final int workEfficiency;
    private static final boolean RUNNING = true;
    private static final int PIZZAIDFROM = 0;
    private static final int PIZZAIDTO = 10;
    private static final Map<Integer, Integer> pizzazPool = new HashMap<>() {
        {
            put(0, 25);
            put(1, 30);
            put(2, 35);
            put(3, 40);
            put(4, 45);
            put(5, 50);
            put(6, 55);
            put(7, 17);
            put(8, 23);
            put(9, 34);
            put(10, 26);
        }
    };

    /**
     * Default constructor
     *
     * @param workEfficiency ECE of worker
     * @param stock          pizzeria stock
     * @param orders         queue
     */
    public Chef(int workEfficiency, Pusher stock, Poper orders) {
        this.workEfficiency = workEfficiency;
        this.orders = orders;
        this.stock = stock;
    }


    @Override
    public void run() {
        while (RUNNING) {
            try {
                Pizza pizza = (Pizza) orders.pop();
                final int pizzaId = pizza.getPizzaId();
                if (pizzaId < PIZZAIDFROM || pizzaId > PIZZAIDTO) {
                    throw new MissingFormatArgumentException("Wrong pizzaId");
                }
                final int orderId = pizza.getOrderId();
                Cook cookService = new CookService(workEfficiency, pizzazPool.get(pizzaId));
                Logger logger = new Logger(orderId, OrderStatus.COOKING);
                logger.log();
                cookService.cook();
                logger = new Logger(orderId, OrderStatus.COOKED);
                logger.log();
                stock.push(pizza);
                logger = new Logger(orderId, OrderStatus.WAITINGFORDELIVERING);
                logger.log();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}