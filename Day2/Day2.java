package Day2;

import java.io.File;
import java.util.Scanner;

public class Day2 {
    public static int ValidPasswords(Scanner input_scan) {
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

        return num_valid;
    }

    public static void main(String[] args) throws Exception {
        String file_path = "./Day2/input_day2.txt";

        try {
            Scanner input_scan = new Scanner(new File(file_path));
            System.out.println("Valid passwords: " + ValidPasswords(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
