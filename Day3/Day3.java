package Day3;

import java.io.File;
import java.util.Scanner;

class Day3 {
    /**
     * The toboggan can only travel at a slope of -3!
     * The given scanner points to a file of the hill, where the
     * hill's topology repeats infitely to the right, and each entry
     * per line is either a '.' or a '#', where a '#' is a tree.
     * 
     * @param input_scan
     * @return Number of trees encountered traversing down with slope -3.
     */
    public static int countTrees(Scanner input_scan) {
        String line = "";

        if (!input_scan.hasNextLine()) {
            System.out.println("File pointed to by input_scan had no line entries..");
            System.exit(0);
        } else {
            line = input_scan.nextLine();
        }

        int line_length = line.length();
        int tree_count = 0;
        int column     = 0;

        while (input_scan.hasNextLine()) {
            line = input_scan.nextLine();
            if (line.charAt(column) == '#') {
                tree_count++;
            }

            column = (column+3)%line_length;
        }

        return tree_count;
    }
    

    public static void main(String[] args) throws Exception {
        String file_name = "input_day3.txt";
        
        try {
            Scanner input_scan = new Scanner(new File(file_name));
            System.out.println("Trees encountered: " + countTrees(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}