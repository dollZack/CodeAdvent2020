package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Day9 {

    private static long encodeError(Scanner input_scan) {
        /**
         * First recieve preamble of 25 numbers....
         */
        ArrayDeque<Long> last_25 = new ArrayDeque<Long>(25);
        int i = 0;
        while (i < 25) {
            last_25.add(input_scan.nextLong());
            i++;
        }

        // System.out.println(last_25.toString());
        
        /*
         * Then, each number recieved should be the sum of any two of 25 most immediate
         * numbers... These two numbers will have different values...
         * 
         * Find the first number in the list which does not follow this encoding
         * 
         */

        Boolean valid = true;
        long next_num = 0;
        while (valid) {
            next_num = input_scan.nextLong();
            valid = checkNumber(next_num, last_25);
        }

        return next_num;
    }


    /**
     * Checks if next_num is a sum of any two of the numbers in ArrayDeque.
     * Adds next_num to last_25, and then returns the result of the check described.
     * @param next_num
     * @param last_25
     * @return Boolean of if next_num is a sum of any two numbers in last_25
     */
    private static Boolean checkNumber(long next_num, ArrayDeque<Long> last_25) {
        
        // find all the sums
        HashSet<Long> sums = new HashSet<Long>();
        Long[] last_25_arr = last_25.toArray(new Long[25]);
        for (int i = 0; i < last_25.size()-1; i++) {
            for (int j = i+1; j < last_25.size(); j++) {
                sums.add(last_25_arr[i] + last_25_arr[j]);
            }
        }

        // check the sums
        last_25.add(next_num);
        
        return sums.contains(next_num);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input_scan = new Scanner(new File("./Day9/day9_input.txt"));
        System.out.println("Incorrect number: " + encodeError(input_scan));
    }
}
