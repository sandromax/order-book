package test.sandromax.orderbook;

import test.sandromax.orderbook.io.FileHandler;
import test.sandromax.orderbook.io.IOHandler;
import test.sandromax.orderbook.stock.Stock;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {

        Stock stock = new Stock();
        IOHandler handler = new FileHandler();

        try {
            stock.handleInputFile(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
