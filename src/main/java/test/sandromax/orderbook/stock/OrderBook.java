package test.sandromax.orderbook.stock;

import java.util.Map;
import java.util.TreeMap;

public class OrderBook {

    // key - price, value - size
    private TreeMap<Integer, Integer> bid;
    private TreeMap<Integer, Integer> ask;

    public OrderBook() {
        bid = new TreeMap<>();
        ask = new TreeMap<>();
    }

    public int getBidSize() {
        return bid.size();
    }

    public int getAskSize() {
        return ask.size();
    }

    public void updateBid(int price, int size) {
        bid.put(price, size);
    }

    public void updateAsk(int price, int size) {
        ask.put(price,size);
    }

    public Map.Entry<Integer, Integer> getBestBidPriceAndSize() {
        return bid.lastEntry();
    }

    public Map.Entry<Integer, Integer> getBestAskPriceAndSize() {
        return ask.firstEntry();
    }

    public Integer getSizeByPriceBid(int price) {
        return bid.get(price);
    }

    public Integer getSizeByPriceAsk(int price) {
        return ask.get(price);
    }

    public Boolean makeBuyOrderBySize(int size) {

        if(ask.size() > 0) {
            for(Map.Entry<Integer, Integer> entry : ask.descendingMap().entrySet()) {
                if(entry.getValue() <= size) {
                    updateAsk(entry.getKey(), (entry.getValue() - size));
                    return true;
                }
            }
        }

        return false;
    }

    public Boolean makeSellOrderBySize(int size) {
        if(bid.size() > 0) {
            for(Map.Entry<Integer, Integer> entry : bid.entrySet()) {
                if(entry.getValue() <= size) {
                    updateBid(entry.getKey(), (entry.getValue() - size));
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderBook orderBook = (OrderBook) o;

        if (bid != null ? !bid.equals(orderBook.bid) : orderBook.bid != null) return false;
        return ask != null ? ask.equals(orderBook.ask) : orderBook.ask == null;
    }

    @Override
    public int hashCode() {
        int result = bid != null ? bid.hashCode() : 0;
        result = 31 * result + (ask != null ? ask.hashCode() : 0);
        return result;
    }
}
