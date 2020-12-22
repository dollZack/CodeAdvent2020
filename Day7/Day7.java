package Day7;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day7 {
    /**
     * We've landed at our first airport, only to discover the have stringent bag color-
     * coding rules. These rules describe the color and amount of bags which should be contained 
     * within a given bag of some color. We have a shiny gold bag, and must find the number of possible
     * colors of outer bags which can contain this shiny gold bag, subject to the rules. 
     * @param input_scan
     * @return Number of colors of bags which can contain our shiny gold one.
     */
    private static int handyHaversacks(Scanner input_scan) {
        int possible_colors = 0;

        /* 
        Each line contains a rule of the form:
        <two-word color> 'bags contain ' <list of comma seperated (<integer> <two-word color> 'bag/bags')>

        This seems like we could make a tree where the bags which can be contained within a particular bag are
        the given bag's immediate descendants.

        But this actually probably would not work as multiple bags can carry mutual colors.

        This could be a table of some kind - say, a hashmap - where a key is a color bag, and 
        it's value is a structure to hold the possible contents...

        We aren't interested in the actual values for each bag, but rather just the colors
        
        */
        HashMap<String, ArrayList<String>> color_map = parseFile(input_scan);


        /* 
        Now that we have a mapping of which bags are contained within a given color bag,
        we can recurse through finding out if a given color bag contains a silver bag..

        We could create a structure which holds whether or not a given color does or does not
        contain it, in a dynamic programming-like fashion...

        */

        return possible_colors;
    }

    private static HashMap<String, ArrayList<String>> parseFile(Scanner input_scan) {
        HashMap<String, ArrayList<String>> color_map = new HashMap<String, ArrayList<String>>();
        String[] curr_line;
        while (input_scan.hasNextLine()) {
            curr_line = input_scan.nextLine().split(" bags contain ");
            String key = curr_line[0];
            ArrayList<String> values = new ArrayList<String>();
            curr_line = curr_line[1].split(", ");
            for (String color : curr_line) {
                String[] curr_color = color.split(" ");
                values.add(curr_color[1] + " " + curr_color[2]);
            }

            color_map.put(key, values);
        }

        return color_map;
    }

    public static void main(String[] args) throws Exception {
        String file_path = "./Day7/day7_input.txt";
        try {
            Scanner input_scan = new Scanner(new File(file_path));
            System.out.println("Possible colors of outward bags: " + handyHaversacks(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
