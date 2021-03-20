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
        int changed_seats = 0;
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
            String[] row = seating_chart.get(i); // row to be updated
            for (int j = 0; j < row.length; j++) { // for every chair in this row
                
                int occupied = occupiedNearby(seating_chart, i, j);

                if (row[j].equals("L") && occupied == 0) {
                    row[j] = "#";
                    changed_seats++;
                } else if (row[j].equals("#") && occupied >= 4) {
                    row[j] = "L";
                    changed_seats++;
                } else {
                    //nothing
                }

                // update row before continuing
                seating_chart.set(i, row);
            }
        }

        return changed_seats;
    }


    private static int occupiedNearby(ArrayList<String[]> seating_chart, int i, int j) {
        String[] curr_row = seating_chart.get(i);

        int occupied = 0;
        // look above
        if (i > 0) {
            String[] above = seating_chart.get(i-1);
            if (j > 0 && above[j-1].equals("#")) occupied++; // top left
            if (above[j].equals("#")) occupied++; // above
            if (j < above.length-1 && above[j+1].equals("#")) occupied++; // top right
        }

        // look at adjacent
        if (j > 0 && curr_row[j-1].equals("#")) occupied++; // left
        if (j < curr_row.length-1 && curr_row[j+1].equals("#")) occupied++; // right

        // look below
        if (i < seating_chart.size()-1) {
            String[] below = seating_chart.get(i+1);
            if (j > 0 && below[j-1].equals("#")) occupied++; // bottom left
            if (below[j].equals("#")) occupied++; // below
            if (j < below.length-1 && below[j+1].equals("#")) occupied++; // bottom right
        }

        return occupied;
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
            //DEBUG
            System.out.println("seats changed: " + seats_changed);
            //DEBUG
        }

        //DEBUG
        // System.out.println(countSeats(seating_chart));
        //DEBUG
    }

    public static void main(String[] args) throws FileNotFoundException {
        String fn = "./Day11/day11_test_input.txt";
        Scanner input_scan = new Scanner(new File(fn));
        seatingSystem(input_scan);
    }
}
