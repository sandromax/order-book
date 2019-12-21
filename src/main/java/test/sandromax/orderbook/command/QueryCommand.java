package test.sandromax.orderbook.command;

import test.sandromax.orderbook.io.IOHandler;
import test.sandromax.orderbook.stock.OrderBook;

import java.io.IOException;
import java.util.Map;

public class QueryCommand implements Command {

    private OrderBook orderBook;
    IOHandler handler;
    private String best;
    private Integer price;
    private Boolean isBestPrice;

    public QueryCommand(OrderBook orderBook, IOHandler handler, String best) {
        this.orderBook = orderBook;
        this.handler = handler;
        this.best = best;
        isBestPrice = true;

        price = -999;
    }

    public QueryCommand(OrderBook orderBook, IOHandler handler, int price) {
        this.orderBook = orderBook;
        this.handler = handler;
        this.price = price;
        isBestPrice = false;

        best = "";
    }

    @Override
    public void invoke() {
        if(isBestPrice || (best.equals("best_bid"))) {
            if(orderBook.getBidSize() > 0) {
                Map.Entry<Integer, Integer> entry = orderBook.getBestBidPriceAndSize();
                try {
                    handler.write(entry.getKey() + "," + entry.getValue());
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if(isBestPrice || (best.equals("best_ask"))) {
            if(orderBook.getAskSize() > 0) {
                Map.Entry<Integer, Integer> entry = orderBook.getBestAskPriceAndSize();
                try {
                    handler.write(entry.getKey() + "," + entry.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if(orderBook.getBidSize() > 0) {
                try {
                    handler.write(orderBook.getSizeByPriceBid(price).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueryCommand that = (QueryCommand) o;

        if (orderBook != null ? !orderBook.equals(that.orderBook) : that.orderBook != null) return false;
        if (best != null ? !best.equals(that.best) : that.best != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return isBestPrice != null ? isBestPrice.equals(that.isBestPrice) : that.isBestPrice == null;
    }

    @Override
    public int hashCode() {
        int result = orderBook != null ? orderBook.hashCode() : 0;
        result = 31 * result + (best != null ? best.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isBestPrice != null ? isBestPrice.hashCode() : 0);
        return result;
    }
}
