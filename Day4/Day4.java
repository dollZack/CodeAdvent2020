package Day4;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Day4 {
    /**
     * The security is struggling to figure out which passports are valid!
     * A valid passport contains a byr, iyr, eyr, hgt, hcl, ecl, pid and cid.
     * We're gonna help them identify valid passports more quickly. But b/c
     * We only have North Pole credentials, which don't have cid's, we'll call
     * those who are only missing cid's valid.
     * 
     * This function reads through the file of passports and returns the number
     * which are valid, subject to our definition ;) Each field is space delimited,
     * of the form <field>:<value>, and each passport is seperated by a blank line.
     * 
     * Edit for puzzle 2: Furthermore, every field has a strict format, it must follow
     * to be considered valid. Thus, a valid passport must contain every field, and every
     * field must follow it's correct format.
     * @param input_scan
     * @return Number of 'valid' passports
     */
    public static int passportChecks(Scanner input_scan) {
        String[] curr_fields;
        String          line             = input_scan.hasNextLine() ? input_scan.nextLine() : null;
        int             valid_passports  = 0;
        HashMap<String, String> curr_credentials = new HashMap<String, String>();

        while(line != null) {
            if (line.equals("")) {
                if (Validations.validPassport(curr_credentials)) {
                    valid_passports++;
                }
                
                curr_credentials.clear();
                
            } else {
                curr_fields = line.split(" ");
                for (String field : curr_fields) {
                    String[] key_value = field.split(":");
                    curr_credentials.put(key_value[0], key_value[1]);
                }
            }

            line = input_scan.hasNextLine() ? input_scan.nextLine() : null;
        }

        // check current credentials at the end of the file!
        if (Validations.validPassport(curr_credentials)) {
            valid_passports++;
        }

        return valid_passports;
    }

    public static void main(String[] args) throws Exception {
        String file_name = "./Day4/day4_input.txt";

        try {
            Scanner input_scan = new Scanner(new File(file_name));
            System.out.println("Valid passports: " + passportChecks(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}