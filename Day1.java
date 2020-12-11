import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

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

        HashSet<Integer> report_entries = new HashSet<Integer>();
        int product = -1;
        int line;
        while (input_scan.hasNext()) {
            line = input_scan.nextInt();
            report_entries.add(line);
        }

        int num = 0;
        while (num <= 1010 && product == -1) {
            if (report_entries.contains(num) && report_entries.contains(2020-num)) {
                product = num*(2020-num);
            }
            num++;
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
        } catch (Exception e) {
            System.out.println("Error opening file:");
            e.printStackTrace();
            System.exit(0);
        }
        
    }
}
