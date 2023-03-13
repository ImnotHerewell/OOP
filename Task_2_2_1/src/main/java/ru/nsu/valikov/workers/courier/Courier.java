package ru.nsu.valikov.workers.courier;

import java.util.Set;
import java.util.TreeSet;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.orders.services.popfortime.PoperForTime;
import ru.nsu.valikov.workers.courier.services.CourierDrive;
import ru.nsu.valikov.workers.courier.services.CourierFill;
import ru.nsu.valikov.workers.courier.services.Drive;
import ru.nsu.valikov.workers.courier.services.Fill;

/**
 * Driver, takes pizzaz from the stock -> takes them to the clients -> returns.
 */
public class Courier extends Thread {
    private static volatile boolean running = true;
    private final int trunkCapacity;
    private final PoperForTime stock;
    private final Set<Order> takenOrders;


    /**
     * Default constructor, gratz to reviewdog 🐶.
     *
     * @param stock         pizzeria stock
     * @param trunkCapacity how much pizzaz courier can take
     */
    public Courier(PoperForTime stock, int trunkCapacity) {
        this.trunkCapacity = trunkCapacity;
        this.stock = stock;
        takenOrders = new TreeSet<>();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Fill filler = new CourierFill(trunkCapacity, stock, takenOrders);
                filler.fill();
                Drive driver = new CourierDrive(takenOrders);
                driver.drive();
                takenOrders.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }
}
