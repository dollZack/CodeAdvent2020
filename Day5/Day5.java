package Day5;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class Day5 {

    /**
     * Time to board! Let's make sure our seats are correct...
     * The plane's boarding passes use binary partitioning, where we
     * have strings of 10 characters of (F)ront, (B)ack, (L)eft, (R)ight.
     * You parse the first 7 characters as if doing binary traversal through
     * the plane's 128 (0 through 127) rows. Then you do the same for the 8 (0-7)
     * columns. A given seat number is row_number*8 + column_number
     *  
     * @param input_scan
     * @return The highest seat number in the given file 
     */
    private static int binaryBoarding(Scanner input_scan) {
        int max_seat = 0;
        
        while (input_scan.hasNextLine()) {
            String curr_line = input_scan.nextLine();
            int curr_seat    = seatNumber(curr_line);
            max_seat         = curr_seat > max_seat ? curr_seat : max_seat;
        }

        return max_seat;
    }


    /**
     * The plane is missing seats in the front and back... Normally we'd find
     * the missing seat on the boarding pass and that would be ours, but these other
     * missing seats complicate the matters. B/c our seat is not on either end, we know
     * the missing seat on the boarding pass who has adjacent neighbors is ours.
     * @param input_scan
     * @return Our seat number
     */
    private static int finalBoarding(Scanner input_scan) {
        /**
         * Thinking that I need to add every seat on the boarding pass to a hashset, and
         * then iterate through the possible seats. If we find one which is not in our set,
         * we check if the set contains the neighbors. If so, return this number!
         */

        HashSet<Integer> boarding_pass = new HashSet<Integer>();
        while (input_scan.hasNextLine()) {
            boarding_pass.add(seatNumber(input_scan.nextLine()));
        }
    }


    /**
     * Parses given string to find row and column of seat
     * @param curr_string
     * @return Seat number
     */
    protected static int seatNumber(String curr_string) {
        int row = rowSearch(curr_string);
        int column = columnSearch(curr_string);
        return row*8 + column;
    }

    /**
     * Performs binary-search like iteration through the first 7 characters
     * of the given string to find the row of the seat.
     * @param curr_string
     * @return Row number
     */
    protected static int rowSearch(String curr_string) {
        int left = 0;
        int right = 127;
        for (int i = 0; i < 7; i++) {
            if (Character.compare('F', curr_string.charAt(i)) == 0) {
                right = left + (right-left)/2;
            } else if (Character.compare('B', curr_string.charAt(i)) == 0) {
                left = right - (right-left)/2;
            } else {
                System.out.println("UNEXPECTED CHARACTER IN rowSearch.");
                System.exit(0);
            }
        }

        return left; // I think by here left and right should be equal?
    }

    /**
     * Performs binary-search like iteration through the last 3 characters
     * of the given string to find the column of the seat.
     * @param curr_string
     * @return Column number
     */
    protected static int columnSearch(String curr_string) {
        int left = 0;
        int right = 7;
        for (int i = 7; i < 10; i++) {
            if (Character.compare('L', curr_string.charAt(i)) == 0) {
                right = left + (right-left)/2;
            } else if (Character.compare('R', curr_string.charAt(i)) == 0) {
                left = right - (right-left)/2;
            } else {
                System.out.println("UNEXPECTED CHARACTER IN rowSearch.");
                System.exit(0);
            }
        }

        return left;
    }

    public static void main(String[] args) throws Exception {
        String file_path = "./Day5/day5_input.txt";

        try {
            Scanner input_scan = new Scanner(new File(file_path));
            System.out.println("Max row: " + binaryBoarding(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}