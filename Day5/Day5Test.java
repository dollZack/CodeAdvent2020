package Day5;

public class Day5Test {
    private static void testRowEquals(String test_string, int row) {
        System.out.print("Testing for string " + test_string + " to return row: " + row + "...");
        String result = Day5.rowSearch(test_string) == row ? "PASSED" : "FAILED";
        System.out.println(result);
    }

    private static void testColumnEquals(String test_string, int column) {
        System.out.print("Testing for string " + test_string + " to return column: " + column + "...");
        String result = Day5.columnSearch(test_string) == column ? "PASSED" : "FAILED";
        System.out.println(result);
    }

    private static void testSeatEquals(String test_string, int seat) {
        System.out.print("Testing for string " + test_string + " to return seat: " + seat + "...");
        String result = Day5.seatNumber(test_string) == seat ? "PASSED" : "FAILED";
        System.out.println(result);
    }

    public static void main(String[] args) {
        testRowEquals("BFFFBBFRRR", 70);
        testRowEquals("FFFBBBFRRR", 14);
        testRowEquals("BBFFBBFRLL", 102);

        testColumnEquals("BFFFBBFRRR", 7);
        testColumnEquals("FFFBBBFRRR", 7);
        testColumnEquals("BBFFBBFRLL", 4);

        testSeatEquals("BFFFBBFRRR", 567);
        testSeatEquals("FFFBBBFRRR", 119);
        testSeatEquals("BBFFBBFRLL", 820);
    }
}