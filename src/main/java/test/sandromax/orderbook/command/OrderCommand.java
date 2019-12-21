package test.sandromax.orderbook.command;

import test.sandromax.orderbook.stock.OrderBook;

public class OrderCommand implements Command {

    private OrderBook orderBook;
    private String operation;
    private int size;

    public OrderCommand(OrderBook orderBook, String operation, int size) {
        this.orderBook = orderBook;
        this.operation = operation;
        this.size = size;
    }

    @Override
    public void invoke() {
        if(operation.equals("buy")) {
            orderBook.makeBuyOrderBySize(size);
        } else if(operation.equals("sell")) {
            orderBook.makeSellOrderBySize(size);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderCommand that = (OrderCommand) o;

        if (size != that.size) return false;
        if (orderBook != null ? !orderBook.equals(that.orderBook) : that.orderBook != null) return false;
        return operation != null ? operation.equals(that.operation) : that.operation == null;
    }

    @Override
    public int hashCode() {
        int result = orderBook != null ? orderBook.hashCode() : 0;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + size;
        return result;
    }
}
