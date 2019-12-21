package test.sandromax.orderbook.io;

import java.io.IOException;
import java.util.LinkedList;

public interface IOHandler {

    LinkedList<String> read() throws IOException ;

    boolean write(String row) throws IOException ;

    boolean clear() throws IOException;

}
