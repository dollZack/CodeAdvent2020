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

        // First line out of while b/c needed line_length
        if (line.charAt(column) == '#') {
            tree_count++;
        }

        column = (column+3)%line_length;

        // Traverse rest of hill!
        while (input_scan.hasNextLine()) {
            line = input_scan.nextLine();
            if (line.charAt(column) == '#') {
                tree_count++;
            }

            column = (column+3)%line_length;
        }

        return tree_count;
    }

    /**
     * Now traverse the hill with varying slopes, returning the product trees 
     * encountered of these slopes
     * @param file_name
     * @return Product
     * @throws Exception
     */
    public static long slopeComparison(String file_name) throws Exception{
        long count_product = 1;
        int[][] slopes    = {
            {1,1}, {3,1}, {5,1}, {7,1}, {1,2}
        };

        for (int[] slope : slopes) {
            int column_offset = slope[0];
            int row_offset    = slope[1];

            try {
                Scanner input_scan = new Scanner(new File(file_name));

                String line   = "";
                int column    = 0;
                int num_trees = 0;

                if (!input_scan.hasNextLine()) {
                    System.out.println("File pointed to by input_scan had no line entries..");
                    System.exit(0);
                } else {
                    line = input_scan.nextLine();
                }

                if (line.charAt(column) == '#') {
                    num_trees++;
                }

                column = (column+column_offset)%line.length();

                // traverse rest of hill
                while(input_scan.hasNextLine()) {
                    int rows_viewed = 0;

                    //go down row_offset lines
                    while(input_scan.hasNextLine() && rows_viewed < row_offset) {
                        line = input_scan.nextLine();
                        rows_viewed++;
                    }
                    
                    if (rows_viewed == row_offset) { // if not true, we have read through the entire file
                        // tree?
                        if (line.charAt(column) == '#') {
                            num_trees++;
                        }
                    }

                    // increment column
                    column = (column+column_offset)%line.length();
                }

                // Finished hill with current slope
                count_product*=num_trees;

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        return count_product;
    }
    

    public static void main(String[] args) throws Exception {
        String file_name = "./Day3/input_day3.txt";
        
        try {
            Scanner input_scan = new Scanner(new File(file_name));
            System.out.println("Trees encountered: " + countTrees(input_scan));
            System.out.println("Tree count product: " + slopeComparison(file_name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}