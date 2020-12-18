package Day7;

import java.io.File;
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

        return possible_colors;
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
