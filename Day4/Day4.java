package Day4;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import java.util.Iterator;

public class Day4 {
    static final HashSet<String> valid_requirements = new HashSet<String>(Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid"));
    static final HashSet<String> valid_eyes         = new HashSet<String>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
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
            String curr_value = curr_credentials.get(curr_cred);
            int i; // used for iterative purposes in switch cases

            switch (curr_cred) {
                case "byr":
                    if (Integer.parseInt(curr_value) < 1920 || Integer.parseInt(curr_value) > 2002) {
                        valid = false;
                    }
                    break;

                case "iyr":
                    if (Integer.parseInt(curr_value) < 2010 || Integer.parseInt(curr_value) > 2020) {
                        valid = false;
                    }
                    break;

                case "eyr":
                    if (Integer.parseInt(curr_value) < 2020 || Integer.parseInt(curr_value) > 2030) {
                        valid = false;
                    }
                    break;
                    
                case "hgt":
                    i = 0;
                    while (i < curr_value.length() && Character.isDigit(curr_value.charAt(i))) {
                        i++;
                    }
                    
                    if (!curr_value.substring(i).equals("cm") && !curr_value.substring(i).equals("in")) {
                        valid = false;
                    }
                    break;

                case "hcl":
                    if (curr_value.charAt(0) != '#') {
                        valid = false;
                    } else {
                        i = 1;
                        //every character must be 0-9 or a-f
                        while (valid && i < curr_value.length()) {
                            if (!Character.isDigit(curr_value.charAt(i)) && !(curr_value.charAt(i) >= 'a' && curr_value.charAt(i) <= 'f') ) {
                                valid = false;
                            }

                            i++;
                        }
                    }
                    break;

                case "ecl":
                    valid = valid_eyes.contains(curr_value); // if we're here, so far the credentials are valid, so assignment to true doesn't hurt anything
                    break;

                case "pid":
                    if (curr_value.length() != 9) {
                        valid = false;
                    }

                    i = 0;
                    while (valid && i < curr_value.length()) {
                        if ( !(curr_value.charAt(i) >= '0' && curr_value.charAt(i) <= '9') ) {
                            valid = false;
                        }

                        i++;
                    }
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
                if (validPassport(curr_credentials)) {
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
        if (validPassport(curr_credentials)) {
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