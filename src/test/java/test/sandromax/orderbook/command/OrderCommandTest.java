package test.sandromax.orderbook.command;

import org.junit.Test;
import test.sandromax.orderbook.stock.OrderBook;

import static org.junit.Assert.*;

public class OrderCommandTest {

    @Test
    public void invokeMethodToBuy() {
        OrderBook orderBook1 = new OrderBook();
        orderBook1.updateAsk(30, 50);
        orderBook1.makeBuyOrderBySize(20);

        OrderBook orderBook2 = new OrderBook();
        orderBook2.updateAsk(30,50);
        OrderCommand orderCommand = new OrderCommand(orderBook2, "buy", 20);
        orderCommand.invoke();

        assertEquals(orderBook1, orderBook2);
    }

    @Test
    public void invokeMethodToSell() {
        OrderBook orderBook1 = new OrderBook();
        orderBook1.updateBid(30, 50);
        orderBook1.makeSellOrderBySize(20);

        OrderBook orderBook2 = new OrderBook();
        orderBook2.updateBid(30,50);
        OrderCommand orderCommand = new OrderCommand(orderBook2, "sell", 20);
        orderCommand.invoke();

        assertEquals(orderBook1, orderBook2);
    }

}