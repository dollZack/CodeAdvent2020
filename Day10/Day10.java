import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Day10 {
    
    /**
     * Your battery is dead, and the power outlet produces the wrong number of jolts.
     * We must perform some foolery to with our own jolt adapters, to ensure that we can
     * charge our device. 
     * 
     * Our device's built-in joltage adapter has a rating of 3 greater
     * than our biggest joltage adapter, and a given joltage adapter can only connect
     * to one that is 1-3 jolts greater than it. The outlet near us has a joltage of 0. 
     * 
     * We must find the sequence of connections between our adaptors, and keep track
     * of the differences between every connection - storing the number of 1 jolt 
     * differences and the number of 3 jolt differences, multiply these, and return.
     * 
     * @param input_scan A list of our joltage adapters.
     */
    private static void adapterArray(Scanner input_scan) {
        /**
         * We have to use every adapter, which implies that there should be no 
         * difference conflicts with our constraint of 1-3 jolt differences.
         * 
         * So we should be able to read the file into a list, sort the list,
         * and then work through it sequentially.
         */

        ArrayList<Integer> adapters_list = new ArrayList<Integer>();
        adapters_list.add(0);
        
        while (input_scan.hasNextInt()) {
            adapters_list.add(input_scan.nextInt());
        }

        adapters_list.sort(Comparator.naturalOrder());
        // add devices joltage, max item + 3, to end of list
        adapters_list.add(adapters_list.size(), adapters_list.get(adapters_list.size()-1)+3);

        //DEBUG
        // System.out.println(adapters_list.toString());
        //DEBUG

        int diff_1, diff_3;
        diff_1 = diff_3 = 0;

        int prev_adapter = adapters_list.get(0);
        int next_adapter;
        for (int next_index = 1; next_index < adapters_list.size(); next_index++) {
            next_adapter = adapters_list.get(next_index);
            int curr_diff = next_adapter - prev_adapter;
            if (curr_diff == 1) {
                diff_1++;
            } else if (curr_diff == 3) {
                diff_3++;
            }

            prev_adapter = next_adapter;
        }

        System.out.println("diff_1: " + diff_1 + ", diff_3: " + diff_3);
        System.out.println("diff product: " + diff_1*diff_3);

    }

    public static void main(String[] args) throws FileNotFoundException {
        String input_string = "./Day10/day10_input.txt";
        Scanner input_scan = new Scanner(new File(input_string));
        adapterArray(input_scan);
    }
}
