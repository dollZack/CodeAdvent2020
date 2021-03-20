package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {

    private static void printSeats(ArrayList<String[]> chart) {
        for (String[] row : chart) {
            for (String seat : row) {
                System.out.print(seat);
            }
            System.out.print("\n");
        }
    }

    private static ArrayList<String[]> chartParser(Scanner input_scan) {
        ArrayList<String[]> seating_chart = new ArrayList<String[]>();
        while (input_scan.hasNextLine()) {
            seating_chart.add(input_scan.nextLine().split(""));
        }

        return seating_chart;
    }

    private static int rearrange(ArrayList<String[]> seating_chart) {
        int num_changes = 0;
        /**
         * iterate through all seats...
         * if L, count all adjacent occupied. if count > 0, don't change
         * else if #, count all occupied. if >= 4, change to empty
         * else do nothing
         * 
         * Counting occupied: look left and right. then look to those above, and those
         * below. this involves checking bounds above, then left and right, then
         * below, and left and right.
         * 
         * Make changes to given row as we check. Then write this new row to the ArrayList.
         * 
         */
        for (int i = 0; i < seating_chart.size(); i++) {
            String[] row = seating_chart.get(i);
        }

        return num_changes;
    }

    /**
     * Time to ride a ferry, and nobody is here yet. Time to do some puzzles
     * regarding the seating situations.
     * 
     * There's a set of rules for how people rearrange. The seats are represented in
     * a grid (2d array) of '.', representing no seat, 'L', meaning empty seat, and
     * '#', meaning occupied. Until every seat is finalized, there are rounds of
     * if rearranging subject to the following rules:
     * - If a seat is empty and none of the 8 adjecent seats are occupied, it becomes
     * occupied. 
     * - If a seat is occupied and 4 or more of the adjacent seats are occupied, it
     * becomes empty.
     * - Else, the seat doesn't change
     */
    private static void seatingSystem(Scanner input_scan) {
        ArrayList<String[]> seating_chart = chartParser(input_scan);

        //DEBUG
        // printSeats(seating_chart);
        //DEBUG

        int seats_changed = 1;
        while (seats_changed > 0) {
            seats_changed = rearrange(seating_chart);
        }

        System.out.println(countSeats(seating_chart));
    }

    public static void main(String[] args) throws FileNotFoundException {
        String fn = "./Day11/day11_test_input.txt";
        Scanner input_scan = new Scanner(new File(fn));
        seatingSystem(input_scan);
    }
}
