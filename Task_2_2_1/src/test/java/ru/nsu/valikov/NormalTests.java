package ru.nsu.valikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.junit.jupiter.api.Test;
import ru.nsu.valikov.json.PizzeriaSystem;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.orders.models.Pizza;
import ru.nsu.valikov.orders.services.pop.PopService;
import ru.nsu.valikov.orders.services.pop.Poper;
import ru.nsu.valikov.orders.services.popfortime.PopForTimeService;
import ru.nsu.valikov.orders.services.popfortime.PoperForTime;
import ru.nsu.valikov.orders.services.push.PushService;
import ru.nsu.valikov.orders.services.push.Pusher;
import ru.nsu.valikov.pizzeria.services.Close;
import ru.nsu.valikov.pizzeria.services.PizzeriaCloser;
import ru.nsu.valikov.pizzeria.services.PizzeriaStarter;
import ru.nsu.valikov.pizzeria.services.Start;
import ru.nsu.valikov.workers.chef.Chef;
import ru.nsu.valikov.workers.courier.Courier;

/**
 * Contains only one test.
 * I can do more tests, but not sure that it is really necessary.
 */
public class NormalTests {

    @Test
    void systemStart() {
        String fileName = "exampleOne";
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("jsons/" + fileName + ".json")) {
            ObjectMapper mapper = new ObjectMapper();
            PizzeriaSystem system = mapper.readValue(input, PizzeriaSystem.class);
            BlockingQueue<Order> stock = new ArrayBlockingQueue<>(system.getStockCapacity());
            Pusher stockPush = new PushService(stock);
            PoperForTime stockPop = new PopForTimeService(stock);
            BlockingQueue<Order> orders = new LinkedBlockingDeque<>();
            Pusher ordersPush = new PushService(orders, new Pizza(1, 10, 20), new Pizza(2, 34, 78),
                                                new Pizza(3, 27, 1000), new Pizza(4, 8756, 347),
                                                new Pizza(5, 236, 512), new Pizza(6, 7452, 1032),
                                                new Pizza(7, 7, 7777), new Pizza(8, 642, 20));
            Poper ordersPop = new PopService(orders);
            List<Courier> couriers = new ArrayList<>();
            List<Chef> chefs = new ArrayList<>();
            system.getChefsWorkEfficiency().forEach(
                    efficiency -> chefs.add(new Chef(efficiency, stockPush, ordersPop)));
            system.getCouriersTrunkCapacity().forEach(
                    capacity -> couriers.add(new Courier(stockPop, capacity)));
            Start starter = new PizzeriaStarter(couriers, chefs);
            starter.start();
            Thread.sleep(24 * 60 * 60);
            Close closer = new PizzeriaCloser(couriers, chefs, orders, stock);
            closer.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
