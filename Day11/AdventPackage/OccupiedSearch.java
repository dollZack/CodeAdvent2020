package AdventPackage;

import java.util.ArrayList;

public class OccupiedSearch {

    /**
     * Searches diagonally upwards/left from seat [i,j], for an occupied seat.
     * @param chart
     * @param i
     * @param j
     * @return True if found, false if not
     */
    public static boolean upLeft(ArrayList<String[]> chart, int i, int j) {
        int row = i-1;
        int col = j-1;

        while (row >= 0 && col >= 0) {
            String seat = chart.get(row)[col];
            if (seat.equals("#")){
                return true;
            }

            if (seat.equals("L")) {
                return false;
            }

            row--;
            col--;
        }

        return false;
    }

    /**
     * Searches above the given point in chart for an occupied seat.
     * @param chart
     * @param i
     * @param j
     * @return True if found, else if not
     */
    public static boolean above(ArrayList<String[]> chart, int i, int j) {
        for (int row = i-1; row >= 0; row--) {
            String seat = chart.get(row)[j];
            if (seat.equals("#")) {
                return true;
            }

            if (seat.equals("L")) {
                return false;
            }
        }

        return false;
    }


    /**
     * Searches diagonally upwards to the right from seat [i,j] for an occupied seat
     * @param chart
     * @param i
     * @param j
     * @return True if one is found, else if not
     */
    public static boolean upRight(ArrayList<String[]> chart, int i, int j) {
        int row_length = chart.get(0).length;
        int row = i-1;
        int col = j+1;

        while (row >= 0 && col < row_length) {
            String seat = chart.get(row)[col];
            if (seat.equals("#")) {
                return true;
            }

            if (seat.equals("L")) {
                return false;
            }

            row--;
            col++;
        }

        return false;
    }


    /**
     * Searches to the left of the given seat for an occupied seat.
     * @param chart
     * @param i
     * @param j
     * @return True if one is found, else if not
     */
    public static boolean left(ArrayList<String[]> chart, int i, int j) {
        String[] curr_row = chart.get(i);

        for (int col = j-1; col >= 0; col--) {
            if (curr_row[col].equals("#")) {
                return true;
            }

            if (curr_row[col].equals("L")) {
                return false;
            }
        }

        return false;
    }

    /**
     * Searches to the right of the given seat for an occupied seat.
     * @param chart
     * @param i
     * @param j
     * @return True if one is found, else if not
     */
    public static boolean right(ArrayList<String[]> chart, int i, int j) {
        String[] curr_row = chart.get(i);

        for (int col = j+1; col < curr_row.length; col++) {
            if (curr_row[col].equals("#")) {
                return true;
            }

            if (curr_row[col].equals("L")) {
                return false;
            }
        }

        return false;
    }


    /**
     * Searches diagonally downwards left from the given seat for an occupied seat
     * @param chart
     * @param i
     * @param j
     * @return True if occupied seat found, else false
     */
    public static boolean downLeft(ArrayList<String[]> chart, int i, int j) {
        int row = i+1;
        int col = j-1;

        while (row < chart.size() && col >= 0) {
            String seat = chart.get(row)[col];
            if (seat.equals("#")) {
                return true;
            }

            if (seat.equals("L")) {
                return false;
            }

            row++;
            col--;
        }

        return false;
    }


    /**
     * Searches below the given point in chart for an occupied seat.
     * @param chart
     * @param i
     * @param j
     * @return True if found, else if not
     */
    public static boolean below(ArrayList<String[]> chart, int i, int j) {
        for (int row = i+1; row < chart.size(); row++) {
            String seat = chart.get(row)[j];
            if (seat.equals("#")) {
                return true;
            }

            if (seat.equals("L")) {
                return false;
            }
        }

        return false;
    }


    /**
     * Searches diagonally down and right from the given seat for an occupied one.
     * @param chart
     * @param i
     * @param j
     * @return True if occupied seat is found, else false
     */
    public static boolean downRight(ArrayList<String[]> chart, int i, int j) {
        int row_length = chart.get(0).length;
        int row = i+1;
        int col = j+1;

        while (row < chart.size() && col < row_length) {
            String seat = chart.get(row)[col];
            if (seat.equals("#")) {
                return true;
            }

            if (seat.equals("L")) {
                return false;
            }

            row++;
            col++;
        }

        return false;
    }


    /**
     * Prints 'success!' Intended to be used by another class for verification that these methods
     * are accessible
     */
    public static void testPrint() {
        System.out.println("success!");
    }

}
