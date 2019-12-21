package test.sandromax.orderbook.command;

import test.sandromax.orderbook.io.IOHandler;
import test.sandromax.orderbook.io.RowValidator;
import test.sandromax.orderbook.stock.OrderBook;

public class CommandFactory {

    public static Command getCommand(OrderBook orderBook, IOHandler handler, String rowAsCommand) {
        String[] fileRowAsArray = rowAsCommand.split(",");

        if(fileRowAsArray.length < 2) {
            throw new IllegalArgumentException();
        }

        String operationType = fileRowAsArray[0];

        switch (operationType) {
            case "u": {
                if(RowValidator.validateUpdate(fileRowAsArray)) {
                    int price = Integer.parseInt(fileRowAsArray[1]);
                    int size = Integer.parseInt(fileRowAsArray[2]);
                    String type = fileRowAsArray[3];

                    return createUpdateCommand(orderBook, price, size, type);
                } else {
                    throw new IllegalArgumentException();
                }
            }
            case "q":
                if(RowValidator.validateQuery(fileRowAsArray)) {
                    return createQueryCommand(orderBook, handler, fileRowAsArray);
                } else {
                    throw new IllegalArgumentException();
                }
            case "o": {
                if(RowValidator.validateOrder(fileRowAsArray)) {
                    String operation = fileRowAsArray[1];
                    int size = Integer.parseInt(fileRowAsArray[2]);

                    return createOrderCommand(orderBook, operation, size);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private static UpdateCommand createUpdateCommand(OrderBook orderBook, int price, int size, String type) {
        return new UpdateCommand(orderBook, price, size, type);
    }

    private static QueryCommand createQueryCommand(OrderBook orderBook, IOHandler handler, String[] fileRowAsArray) {

        if(fileRowAsArray.length == 2) {
            String bestPrice = fileRowAsArray[1];

            return new QueryCommand(orderBook, handler, bestPrice);
        } else {
            int bestPriceForSize = Integer.parseInt(fileRowAsArray[2]);

            return new QueryCommand(orderBook, handler, bestPriceForSize);
        }

    }

    private static OrderCommand createOrderCommand(OrderBook orderBook, String operation, int size) {
        return new OrderCommand(orderBook, operation, size);
    }

}
