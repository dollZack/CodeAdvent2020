package Day4;

import java.io.File;
import java.util.HashSet;
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
     * @param input_scan
     * @return Number of 'valid' passports
     */
    public static int passportChecks(Scanner input_scan) {
        HashSet<String> valid_requirements = new HashSet<String>();
        valid_requirements.add("byr");
        valid_requirements.add("iyr");
        valid_requirements.add("eyr");
        valid_requirements.add("hgt");
        valid_requirements.add("hcl");
        valid_requirements.add("ecl");
        valid_requirements.add("pid");

        String[] curr_fields;
        String          line             = input_scan.hasNextLine() ? input_scan.nextLine() : null;
        int             valid_passports  = 0;
        HashSet<String> curr_credentials = new HashSet<String>();

        while(line != null) {
            if (line.equals("")) {
                if (curr_credentials.containsAll(valid_requirements)) {
                    valid_passports++;
                }
                
                curr_credentials.clear();
                
            } else {
                curr_fields = line.split(" ");
                for (String field : curr_fields) {
                    curr_credentials.add(field.split(":")[0]);
                }
            }

            line = input_scan.hasNextLine() ? input_scan.nextLine() : null;
        }

        // check current credentials at the end of the file!
        if (curr_credentials.containsAll(valid_requirements)) {
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