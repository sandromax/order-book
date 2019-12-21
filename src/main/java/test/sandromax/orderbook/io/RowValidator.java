package test.sandromax.orderbook.io;

public class RowValidator {

    public static boolean validateUpdate(String[] row) {

        if(row.length == 4) {
            if(row[1].matches("\\d+") && (Integer.parseInt(row[1]) >= 0)) {
                if(row[2].matches("\\d+") && (Integer.parseInt(row[2]) >= 0)) {
                    if(row[3].equals("bid") || row[3].equals("ask")) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    public static boolean validateQuery(String[] row) {

        if(row.length == 2) {
            if(row[1].equals("best_bid") || row[1].equals("best_ask")) {
                return true;
            }
        } else if(row.length == 3) {
            if (row[1].equals("size")) {
                if(row[2].matches("\\d+") && (Integer.parseInt(row[2]) >= 0)) {
                    return true;
                }
            }
        }

        return false;

    }

    public static boolean validateOrder(String[] row) {

        if(row.length == 3) {
            if(row[1].equals("buy") || row[1].equals("sell")) {
                if(row[2].matches("\\d+")  && (Integer.parseInt(row[2]) >= 0)) {
                    return true;
                }
            }
        }

        return false;

    }
}
