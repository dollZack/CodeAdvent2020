package Day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Validations {
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
}
