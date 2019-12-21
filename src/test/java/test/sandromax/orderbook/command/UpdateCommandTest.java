package test.sandromax.orderbook.command;

import org.junit.Test;
import test.sandromax.orderbook.stock.OrderBook;

import static org.junit.Assert.*;

public class UpdateCommandTest {

    @Test
    public void invokeMethodTestWithBid() {
        OrderBook orderBook1 = new OrderBook();
        orderBook1.updateBid(10, 2);

        OrderBook orderBook2 = new OrderBook();
        UpdateCommand updateCommand = new UpdateCommand(orderBook2, 10, 2, "bid");
        updateCommand.invoke();

        assertEquals(orderBook1, orderBook2);
    }

    @Test
    public void invokeMethodTestWithAsk() {
        OrderBook orderBook1 = new OrderBook();
        orderBook1.updateAsk(10, 2);

        OrderBook orderBook2 = new OrderBook();
        UpdateCommand updateCommand = new UpdateCommand(orderBook2, 10, 2, "ask");
        updateCommand.invoke();

        assertEquals(orderBook1, orderBook2);
    }
}