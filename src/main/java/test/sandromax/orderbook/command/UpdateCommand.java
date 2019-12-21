package test.sandromax.orderbook.command;

import test.sandromax.orderbook.stock.OrderBook;

public class UpdateCommand implements Command {

    private OrderBook orderBook;
    private int price;
    private int size;
    private String type;

    public UpdateCommand(OrderBook orderBook, int price, int size, String type) {
        this.orderBook = orderBook;
        this.price = price;
        this.size = size;
        this.type = type;
    }

    @Override
    public void invoke() {
        if(type.equals("bid")) {
            orderBook.updateBid(price, size);
        } else if(type.equals("ask")) {
            orderBook.updateAsk(price, size);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateCommand that = (UpdateCommand) o;

        if (price != that.price) return false;
        if (size != that.size) return false;
        if (orderBook != null ? !orderBook.equals(that.orderBook) : that.orderBook != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = orderBook != null ? orderBook.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + size;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
