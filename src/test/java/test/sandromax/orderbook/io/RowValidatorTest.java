package test.sandromax.orderbook.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class RowValidatorTest {

    @Test
    public void shouldValidateUpdate() {
        String[] row = {"u", "102", "30", "bid"};

        assertTrue(RowValidator.validateUpdate(row));
    }

    @Test
    public void shouldNotValidateUpdateWhenTypo() {
        String[] row = {"u", "102", "30", "bud"};

        assertFalse(RowValidator.validateUpdate(row));
    }

    @Test
    public void shouldValidateQuery() {
        String[] row = {"q", "size", "30"};

        assertTrue(RowValidator.validateQuery(row));
    }

    @Test
    public void shouldNotValidateQueryWhenTypo() {
        String[] row = {"q", "best_asp"};

        assertFalse(RowValidator.validateQuery(row));
    }

    @Test
    public void shouldValidateOrder() {
        String[] row = {"o", "buy", "10"};

        assertTrue(RowValidator.validateOrder(row));
    }

    @Test
    public void shouldNotValidateOrderWhenTypo() {
        String[] row = {"o", "bye", "10"};

        assertFalse(RowValidator.validateOrder(row));
    }
}