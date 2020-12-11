package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.lang.Math;

public class Day1 {
    /**
     * The elves in accounting need me to fix my expense report, located
     * in the file pointed to by the scanner. This function finds the two numbers
     * in the report which sum to 2020, and returns their product.
     * @param input_scan
     * @return The product of the numbers which sum to 2020, or -1 if not found.
     */
    private static int ReportRepair(Scanner input_scan) {
        /* 
        Let's store all entries of the file into a hashset, and then iterate through
        the 1010 possible sums to 2020, exiting once we find the sum. 
        */

        // Populate HashSet
        HashSet<Integer> report_entries = new HashSet<Integer>();
        int product = -1;
        int line;
        while (input_scan.hasNext()) {
            line = input_scan.nextInt();
            report_entries.add(line);
        }

        input_scan.close();

        // Find the pair
        int num = 0;
        while (num <= 1010 && product == -1) {
            if (report_entries.contains(num) && report_entries.contains(2020-num)) {
                product = num*(2020-num);
            }
            num++;
        }

        /* 
        note: this could be optimized by starting with the minimum value in the entry,
        and going to the 1010 or the max, whichever is smaller.
        */

        return product;
    }


    /** 
     * Same as the previous repair, but now by finding three numbers which sum to 2020,
     * rather than two.
     * @param input_scan
     * @return
     */
    public static int ReportRepair2(Scanner input_scan) {
        int product = -1;
        /* 
        I wonder if this can be done similarly to the previous repair...
        The file contains all positive entries.
        Possible sums are 
        1, 1, 2018
        ...
        1, 1009, 1010
        2, 2, 2016
        ...
        2, 1008, 1010
        2, 1009, 1009
        2020-2 = 2018 -> 2018/2 = 1009

        3, 3, 2014
        ...
        3, 1007, 1010
        3, 1008, 1009

        2020 - 3 = 2017 -> 2017/2 = 1008.5
        Bound on when to stop for giving first integer is when (2020-i-j >= ceil((2020-i)/2)),
        where i and j are the first and second integers

        When to stop iterating i?
        2020//3 = 673

        673, 673, 674;
        2020-673= 1347 -> ceil(1347/2) = 674

        

        */

        // Populate HashSet;
        HashSet<Integer> report_entries = new HashSet<Integer>();
        int line;
        while (input_scan.hasNext()) {
            line = input_scan.nextInt();
            report_entries.add(line);
        }

        input_scan.close();

        // Find the 3-tuple
        int i = 0;
        int j, k;
        while (i <= 673 && product == -1) {
            j = i;
            k = 2020-i-j;

            while (k>=(Math.ceil((2020-i)/2)) && product == -1) {
                if (report_entries.contains(i) && report_entries.contains(j) && report_entries.contains(k)) {
                    product = i*j*k;
                }

                j++;
                k--;
            }

            i++;
        }

        return product;
    }


    /**
     * Calls Day 1 Puzzles, exiting on any errors.
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        try {
            Scanner input_scan = new Scanner(new File(args[0]));
            System.out.println("Repairs: " + ReportRepair(input_scan));

            input_scan = new Scanner(new File(args[0]));
            System.out.println("Repairs pt. 2: " + ReportRepair2(input_scan));
            
        } catch (Exception e) {
            System.out.println("Error opening file:");
            e.printStackTrace();
            System.exit(0);
        }
        
    }
}
