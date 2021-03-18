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
        printSeats(seating_chart);
        //DEBUG
    }

    public static void main(String[] args) throws FileNotFoundException {
        String fn = "./Day11/day11_test_input.txt";
        Scanner input_scan = new Scanner(new File(fn));
        seatingSystem(input_scan);
    }
}
