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

    private static ArrayList<String[]> chartCloner(ArrayList<String []> old_chart) {
        // int row_length = old_chart.get(0).length;
        // int num_rows = old_chart.size();
        ArrayList<String[]> chart_clone = new ArrayList<String[]>();
        // for (int row = 0; row < num_rows; row++) {
        for (String[] row : old_chart) {
            chart_clone.add(row.clone());
            // for (int seat = 0; seat < row_length; seat++) {
            //     new_row[seat] = curr_row[]
            // }
        }

        return chart_clone;
    }

    private static ArrayList<String[]> rearrange(ArrayList<String[]> seating_chart) {
        //DEBUG
        // System.out.println("seating chart on rearrange entry: ");
        // printSeats(seating_chart);
        //DEBUG

        int changed_seats = 0;
        ArrayList<String[]> updated_chart = chartCloner(seating_chart);
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
            String[] row = seating_chart.get(i).clone(); // row to be updated
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
                updated_chart.set(i, row);
            }
        }

        //DEBUG
        // System.out.println("\nseats changed: " + changed_seats + "\n");
        // System.out.println("old chart: ");
        // printSeats(seating_chart);
        // System.out.println("\nnew chart: ");
        // printSeats(updated_chart);
        //DEBUG

        return updated_chart;
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

    private static boolean seatsChanged(ArrayList<String[]> prev_chart, ArrayList<String[]> new_chart) {
        boolean same = true;
        for (int row = 0; row < prev_chart.size(); row++) {
            String[] prev_row = prev_chart.get(row);
            String[] new_row = new_chart.get(row);
            for (int seat = 0; seat < prev_chart.get(0).length; seat++) {
                if (!prev_row[seat].equals(new_row[seat])) {
                    same = false;
                }
            }

            if (!same) return true;
        }

        System.out.println("reached end of seatsChanged... 'same' value: " + same);
        return !same;
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
        ArrayList<String[]> prev_chart;
        //DEBUG
        // printSeats(seating_chart);
        //DEBUG

        boolean seats_changed = true;
        while (seats_changed) {
        // for (int i = 0; i < 6; i++) {
            prev_chart = seating_chart;
            seating_chart = rearrange(seating_chart);
            //DEBUG
            System.out.println("seating chart after rearrange: ");
            printSeats(seating_chart);
            
            seats_changed = seatsChanged(prev_chart, seating_chart);
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
