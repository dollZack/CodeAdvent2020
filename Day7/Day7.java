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

        return numContainGold(color_map);

        /* 
        Now that we have a mapping of which bags are contained within a given color bag,
        we can recurse through finding out if a given color bag contains a shiny gold bag..

        We could create a structure which holds whether or not a given color does or does not
        contain it, in a dynamic programming-like fashion...

        */

    }

    /**
     * Builds a hashmap mapping bag colors to colors of bags they contain by reading
     * through the file pointed to by input_scan.
     * @param input_scan
     * @return hashmap<String, ArrayList<String>> mapping bag colors to colors of bags they contain
     */
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
                String new_value = curr_color[1] + " " + curr_color[2];
                if (new_value.compareTo("other bags.") != 0) {
                    values.add(new_value);
                }
            }

            color_map.put(key, values);
        }

        return color_map;
    }

    /**
     * Implements a dynamic programming-like recursion through the color map counting number
     * of bags which contain shiny gold bags.
     * @param color_map
     * @return Number of bags which contain shiny gold bags 
     */
    private static int numContainGold(HashMap<String, ArrayList<String>> color_map) {
        int num_contain = 0;

        // setup table <color> -> [<read yet>, <contains or not>]
        HashMap<String, int[]> contains_table = new HashMap<String, int[]>();
        for (String color : color_map.keySet()) {
            contains_table.put(color, new int[] {0,0}); // first is whether or not we've checked yet, second is true or false
        }

        // recurse through the color values in dynamic-programming fashion using driver
        for (String color : color_map.keySet()) {
            if (contains_table.get(color)[0] == 0) {
                containsDriver(color, contains_table, color_map); // this assumes containsDriver can actually
                                                                // modify the table
            }

            if (contains_table.get(color)[1] == 1) {
                num_contain++;
            }
        }

        return num_contain;

        // if we haven't looked at a color yet, we want to recurse through it's contents
        // every path recurses until it finds that it does or does not contain shiny
        // return 
    }

    /**
     * Find whether or not the given color bag contains shiny gold, updating contains_map
     * while doing so
     * @param color
     * @param contains_map
     * @param color_map
     * @return 1 if color contains shiny gold, 0 if not
     */
    private static int containsDriver(String color, HashMap<String, int[]> contains_map, HashMap<String, ArrayList<String>> color_map){
        if (color.compareTo("shiny gold") == 0) {
            return 1;
        } else if (contains_map.get(color)[0] == 1) {
            // we've already recursed through this color
            return contains_map.get(color)[1];
        } else {
            // recurse through the values of this color, setting our 2nd bit to whether
            // or not one of the bags contain
            int contains = 0;
            // if one of the contained bags can hold shiny gold, we so does this bag
            for (String value : color_map.get(color)) {
                contains = containsDriver(value, contains_map, color_map) == 1 ? 1 : contains;
            }

            contains_map.put(color, new int[] {1,contains}); // 1 -> we've read it, and contains -> either yes or no
            return contains;
        }
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
