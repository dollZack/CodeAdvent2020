package Day2;

import java.io.File;
import java.util.Scanner;

public class Day2 {

    /**
     * The Toboggon shop needs help fixing their passwords...
     * Each line in the file pointed to by the given scanners if of the format
     * <low_bound>:<upper_bound> <rule_char>: <password_string>, where the rule char
     * must occur in the password_string between some amount within the bounds (inclusive).
     * 
     * @param input_scan
     * @return Number of valid passwords found
     */
    public static int validPasswords(Scanner input_scan) {
        int num_valid = 0; // count of valid passwords

        int low_bound, upper_bound, curr_char_count; // used for counting occurrences
        char rule_char; // used for given rule
        String[] line; // given line in file, split by space
        String curr_pass;

        while (input_scan.hasNext()) {
            line = input_scan.nextLine().split(" ");

            low_bound   = Integer.parseInt(String.valueOf(line[0].split("-")[0]));
            upper_bound = Integer.parseInt(String.valueOf(line[0].split("-")[1]));

            rule_char       = line[1].charAt(0);
            curr_char_count = 0;

            curr_pass      = line[2];
            int char_index = 0;

            while (char_index < curr_pass.length() && curr_char_count <= upper_bound) {
                if (curr_pass.charAt(char_index) == rule_char) {
                    curr_char_count++;
                }
                char_index++;
            }

            if (curr_char_count >= low_bound && curr_char_count <= upper_bound) {
                num_valid++;
            }
        }

        input_scan.close();
        return num_valid;
    }


    /**
     * Those were the wrong rules! The format is the same as specified previously,
     * but were <low_bound> and <upper_bound> are actually <index_1> and <index_2>,
     * where exactly one of the the characters at these indexes (1-indexed) must be
     * equal to rule_char.
     * 
     * @param input_scan
     * @return Number of valid passwords
     */
    public static int validPasswords2(Scanner input_scan) {
        int num_valid = 0; // count of valid passwords

        int index_1, index_2, curr_char_count; // used for counting occurrences
        char rule_char; // used for given rule
        String[] line; // given line in file, split by space
        String curr_pass;

        while (input_scan.hasNext()) {
            line = input_scan.nextLine().split(" ");

            index_1   = Integer.parseInt(String.valueOf(line[0].split("-")[0]));
            index_2 = Integer.parseInt(String.valueOf(line[0].split("-")[1]));

            rule_char       = line[1].charAt(0);
            curr_char_count = 0;
            curr_pass       = line[2];

            if (curr_pass.charAt(index_1-1) == rule_char) {
                curr_char_count++;
            }

            if (curr_pass.charAt(index_2-1) == rule_char) {
                curr_char_count++;
            }

            if (curr_char_count == 1) {
                num_valid++;
            }
        }

        input_scan.close();
        return num_valid;
    }

    public static void main(String[] args) throws Exception {
        String file_path = "./Day2/input_day2.txt";

        try {
            Scanner input_scan = new Scanner(new File(file_path));
            System.out.println("Valid passwords (v1): " + validPasswords(input_scan));

            input_scan = new Scanner(new File(file_path));
            System.out.println("Valid passwords (v2): " + validPasswords2(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
