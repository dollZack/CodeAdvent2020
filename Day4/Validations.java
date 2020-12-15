package Day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Validations {
    static final HashSet<String> valid_requirements = new HashSet<String>(Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid"));
    static final HashSet<String> valid_eyes         = new HashSet<String>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));


    protected static Boolean validByr(String byr) {
        Boolean valid = true;
        if (Integer.parseInt(byr) < 1920 || Integer.parseInt(byr) > 2002) {
            valid = false;
        }
        
        return valid;
    }

    protected static Boolean validIyr(String iyr) {
        Boolean valid = true;
        if (Integer.parseInt(iyr) < 2010 || Integer.parseInt(iyr) > 2020) {
            valid = false;
        }

        return valid;
    }

    protected static Boolean validEyr(String eyr) {
        Boolean valid = true;
        if (Integer.parseInt(eyr) < 2020 || Integer.parseInt(eyr) > 2030) {
            valid = false;
        }

        return valid;
    }

    protected static Boolean validHgt(String hgt) {
        Boolean valid = false;

        int i = 0;
        while (i < hgt.length() && Character.isDigit(hgt.charAt(i))) {
            i++;
        }
        
        int height_num = Integer.parseInt(hgt.substring(0, i));
        if (hgt.substring(i).equals("cm")) {
            valid = (height_num >= 150 && height_num <= 193);
        } else if (hgt.substring(i).equals("in")) {
            valid = (height_num >= 59 && height_num <= 76);
        } else {
            valid = false;
        }

        return valid;
    }


    protected static Boolean validHcl(String hcl) {
        Boolean valid = false;
        if (hcl.charAt(0) != '#') {
            valid = false;
        } else {
            valid = hcl.length() == 7;

            int i = 1;
            //every character must be 0-9 or a-f
            while (valid && i < hcl.length()) {
                if (!Character.isDigit(hcl.charAt(i)) && !(hcl.charAt(i) >= 'a' && hcl.charAt(i) <= 'f') ) {
                    valid = false;
                }

                i++;
            }
        }
        return valid;
    }

    protected static Boolean validPid(String pid) {
        Boolean valid = false;

        if (pid.length() != 9) {
            valid = false;
        }

        int i = 0;
        while (valid && i < pid.length()) {
            if ( !(pid.charAt(i) >= '0' && pid.charAt(i) <= '9') ) {
                valid = false;
            }

            i++;
        }

        return valid;
    }



    /**
     * Checks if the given credentials contain all of the required credentials,
     * and that every field meets its particular rules.
     * 
     * @param curr_credentials
     * @return True if the credentials are valid, else false.
     */
    public static Boolean validPassport (HashMap<String, String> curr_credentials) {
        Boolean valid = curr_credentials.keySet().containsAll(valid_requirements);

        Iterator<String> required_creds = valid_requirements.iterator();
        while (valid && required_creds.hasNext()) {
            String curr_cred  = required_creds.next();
            String curr_value = curr_credentials.get(curr_cred);

            switch (curr_cred) {
                case "byr":
                    valid = validByr(curr_value);
                    break;

                case "iyr":
                    valid = validIyr(curr_value);
                    break;

                case "eyr":
                    valid = validEyr(curr_value);
                    break;
                    
                case "hgt":
                    valid = validHgt(curr_value);
                    break;

                case "hcl":
                    valid = validHcl(curr_value);
                    break;

                case "ecl":
                    valid = valid_eyes.contains(curr_value); // if we're here credentials thus far are valid, so assignment to true doesn't hurt anything
                    break;

                case "pid":
                    valid = validPid(curr_value);
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
