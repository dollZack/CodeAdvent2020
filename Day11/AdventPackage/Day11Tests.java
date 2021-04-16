package AdventPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11Tests {

    public static void main(String[] args) throws FileNotFoundException {
        test1();
        test2();
        test3();
    }


    private static void test1() throws FileNotFoundException {
        String fn = "./Day11/seat_count_1.txt";
        Scanner input_scan = new Scanner(new File(fn));
        ArrayList<String[]> chart = Day11.chartParser(input_scan);
        System.out.println("Chart:");
        Day11.printSeats(chart);

        System.out.println("Number of occupied seats: " + Day11.occupiedVisible(chart, 4, 3));
    }

    private static void test2() throws FileNotFoundException {
        String fn = "./Day11/seat_count_2.txt";
        Scanner input_scan = new Scanner(new File(fn));
        ArrayList<String[]> chart = Day11.chartParser(input_scan);
        System.out.println("Chart:");
        Day11.printSeats(chart);

        System.out.println("Number of occupied seats: " + Day11.occupiedVisible(chart, 1, 1));
    }

    private static void test3() throws FileNotFoundException {
        String fn = "./Day11/seat_count_3.txt";
        Scanner input_scan = new Scanner(new File(fn));
        ArrayList<String[]> chart = Day11.chartParser(input_scan);
        System.out.println("Chart:");
        Day11.printSeats(chart);

        System.out.println("Number of occupied seats: " + Day11.occupiedVisible(chart, 3, 3));
    }
}

