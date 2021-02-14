package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Day9 {

    private static int EncodeError(Scanner input_scan) {
        /**
         * First recieve preamble of 25 numbers....
         */
        ArrayDeque<Integer> last_25 = new ArrayDeque<Integer>(25);
        int i = 0;
        while (i < 25) {
            last_25.add(input_scan.nextInt());
            i++;
        }

        System.out.println(last_25.toString());
        
        /*
         * Then, each number recieved should be the sum of any two of 25 most immediate
         * numbers... These two numbers will have different values...
         * 
         * Find the first number in the list which does not follow this encoding
         * 
         */

        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input_scan = new Scanner(new File("./Day9/day9_input.txt"));
        System.out.println("Incorrect number: " + EncodeError(input_scan));
    }
}
