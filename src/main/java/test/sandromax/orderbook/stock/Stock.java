package test.sandromax.orderbook.stock;

import test.sandromax.orderbook.io.FileHandler;
import test.sandromax.orderbook.command.Command;
import test.sandromax.orderbook.command.CommandFactory;
import test.sandromax.orderbook.io.IOHandler;

import java.io.IOException;
import java.util.LinkedList;

public class Stock {

    private OrderBook orderBook;

    public Stock() {
        orderBook = new OrderBook();
    }

    public void handleInputFile(IOHandler handler) throws IOException {

        LinkedList<String> rows = handler.read();

        Command command;
        int counter = 0;
        handler.clear();

        for (String row : rows) {
            try {
                command = CommandFactory.getCommand(orderBook, handler, row);
                command.invoke();
                counter++;
            } catch (IllegalArgumentException e) {
                System.out.println("In input file in line " + (counter + 1) + " found an invalid command");
            }
        }

    }

}
