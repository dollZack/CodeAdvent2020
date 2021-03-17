import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Day10 {
    
    private static ArrayList<Integer> generateAdapterList(Scanner input_scan) {
        ArrayList<Integer> adapters_list = new ArrayList<Integer>();
        adapters_list.add(0);
        
        while (input_scan.hasNextInt()) {
            adapters_list.add(input_scan.nextInt());
        }

        adapters_list.sort(Comparator.naturalOrder());
        // add devices joltage, max item + 3, to end of list
        adapters_list.add(adapters_list.size(), adapters_list.get(adapters_list.size()-1)+3);

        return adapters_list;
    }

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

        ArrayList<Integer> adapters_list = generateAdapterList(input_scan);

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

    /**
     * Now we gotta figure out all of the possible valid combinations of adapters.
     */
    private static void adapterCombinations(Scanner input_scan) {
        /**
         * brute force way: recurse through the options, passing around, incrementing and returning
         * a variable indicating distinct recursions, which indicate distinct combinations of adapters.
         * 
         * I think this only works if we increment upon getting to the last item in the array.
         * Edit: no... That might work but I'm not seeing how. I think incrementing per recursion is the way to go.
         * Edit again: Actually, we can increment at the end, and return... Where this result is getting assigned to
         * every recursion. And per recursion level, after performing all possible recursions, we return this
         * as-updated-as-possible count.
         * 
         * Edit again again: This way is too inefficient. We need to prevent re-computing stuff...
         * Our goal is to determine distinct paths... If we start from the right
         */

        // get our list
        ArrayList<Integer> adapters_list = generateAdapterList(input_scan);

        // // recurse
        // int combinations_count, curr_index;
        // combinations_count = curr_index = 0;
        // combinations_count = combinationsDriver(combinations_count, curr_index, adapters_list);

        /**
         * This might be possible if we start from the right side and work backwards in a dynamic programming fashion.
         * If we have an array of the same length as our adapters_list and work backwards, a given index could indicate
         * the number of possible ways to get to the end from that index. Then, by the time we get to the 0th item, we
         * have a count of possible combinations from the beginning (the power outlet).
         * 
         * My thought right now is that we get next (leftward) entry by summing the values of all legitimate would-be 
         * recursions...
         */

        long[] dynamic_list = new long[adapters_list.size()];
        dynamic_list[dynamic_list.length-1] = 1;
        for (int i = dynamic_list.length-2; i >= 0; i--) {
            long count = 0;
            int curr_num = adapters_list.get(i);
            boolean recurse = true;
            int curr_index = i+1;
            // modify recursion logic...
            while (recurse && curr_index <= i+3 && curr_index < adapters_list.size()) {
                if (adapters_list.get(curr_index) - curr_num <= 3){
                    count+=dynamic_list[curr_index];
                    curr_index++;
                } else {
                    recurse = false;
                }
            }

            //update dynamic progress
            dynamic_list[i] = count;
        }

        //DEBUG
        System.out.println(dynamic_list.toString());
        //DEBUG

        System.out.println("Adapter combinations: " + dynamic_list[0]);
    }


    /**
     * Considers the possible next adapters from the given index and recurses through them.
     * The count is incremented when we get to the end of the list, and this is what we return.
     */
    private static int combinationsDriver(int count, int index, ArrayList<Integer> list) {
        // base case
        if (index == list.size()-1) {
            return count+1;
        // recurse
        } else {
            /**
             * There are no duplicate entries, meaning there are a maximum of three possible recursions,
             * being that the next three items are the next three logically sequential integers.
             * We can say this because our list is sorted.
             */
            int curr_num = list.get(index);
            boolean recurse = true;
            int curr_index = index+1;
            while (recurse && curr_index <= index+3 && curr_index < list.size()) {
                if (list.get(curr_index) - curr_num <= 3){
                    count = combinationsDriver(count, curr_index, list);
                    curr_index++;
                } else {
                    recurse = false;
                }
            }

            return count;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String input_string = "./Day10/day10_input.txt";
        Scanner input_scan = new Scanner(new File(input_string));
        // adapterArray(input_scan);
        adapterCombinations(input_scan);
    }
}
