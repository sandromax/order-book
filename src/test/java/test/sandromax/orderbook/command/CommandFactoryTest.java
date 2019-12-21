package test.sandromax.orderbook.command;

import org.junit.BeforeClass;
import org.junit.Test;
import test.sandromax.orderbook.io.FileHandler;
import test.sandromax.orderbook.io.IOHandler;
import test.sandromax.orderbook.stock.OrderBook;

import static org.junit.Assert.*;

public class CommandFactoryTest {

    private static OrderBook orderBook;
    private static IOHandler handler;

    @BeforeClass
    public static void setup() {
        orderBook = new OrderBook();
        handler = new FileHandler();
    }


    @Test
    public void shouldReturnUpdateCommand() {
        UpdateCommand updateCommand = new UpdateCommand(orderBook, 9, 1, "bid");

        assertEquals(updateCommand, CommandFactory.getCommand(orderBook, handler, "u,9,1,bid"));
    }

    @Test
    public void shouldReturnQueryCommand() {
        QueryCommand queryCommand = new QueryCommand(orderBook, handler, 5);
        assertEquals(queryCommand, CommandFactory.getCommand(orderBook, handler, "q,size,5"));

        QueryCommand queryCommand2 = new QueryCommand(orderBook, handler, "best_bid");
        assertEquals(queryCommand2, CommandFactory.getCommand(orderBook, handler, "q,best_bid"));

        QueryCommand queryCommand3 = new QueryCommand(orderBook, handler, "best_ask");
        assertEquals(queryCommand3, CommandFactory.getCommand(orderBook, handler, "q,best_ask"));
    }

    @Test
    public void shouldReturnOrderCommand() {
        OrderCommand orderCommand = new OrderCommand(orderBook, "buy", 3);

        assertEquals(orderCommand, CommandFactory.getCommand(orderBook, handler, "o,buy,3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenRowWithoutComma() {
        CommandFactory.getCommand(new OrderBook(), handler, "obuy3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenRowEmpty() {
        CommandFactory.getCommand(new OrderBook(), handler, "");
    }

}