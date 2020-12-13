package Day4;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import java.util.Iterator;

public class Day4 {
    static final HashSet<String>  valid_requirements = new HashSet<String>(Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid"));

    /**
     * Checks if the given credentials contain all of the required credentials,
     * and that every field meets its particular rules.
     * 
     * @param curr_credentials
     * @return True if the credentials are valid, else false.
     */
    public static Boolean validPassport (HashMap<String, String> curr_credentials) {
        Iterator<String> required_creds = valid_requirements.iterator();
        Boolean valid = curr_credentials.keySet().containsAll(valid_requirements);
        while (valid && required_creds.hasNext()) {
            String curr_cred  = required_creds.next();
            // String curr_value = curr_credentials.get(curr_cred);

            switch (curr_cred) {
                case "byr":
                    break;
                
                case "iyr":

                    break;

                case "eyr":

                    break;

                case "hgt":

                    break;

                case "hcl":

                    break;

                case "ecl":

                    break;

                case "pid":

                    break;
                
                default:
                    System.out.println("Unexpected key in required_credentials...");
                    System.exit(0);
                    break; // never runs
            }
        }

        return valid;
    }


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
                if (curr_credentials.keySet().containsAll(valid_requirements)) {
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
        if (curr_credentials.keySet().containsAll(valid_requirements)) {
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