package Day5;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Day5 {
    
    /**
     * Everybody needs help answering their customs questions! The file pointed to by the given 
     * scanner contains groups of people and the questions to which they answered yes, where a given
     * line is a persons' answers to which they answered yes, and everygroup is seperated by an empty
     * line.
     * 
     * Edit for puzzle 2: Actually need to return number sum of number of questions which everybody
     * answered yes to, per group. 
     * 
     * @param input_scan
     * @return The sum of the number of unique questions answered yes by each group
     */
    private static int customCustoms(Scanner input_scan) {
        /* 
        Now we could have a hashmap for a group, incrememting the value
        per occurence.  Then, once we see a group, our count to add to our sum
        is the number of key's whose value is equal to number of people in the group.
        This also mean we need to keep a count of group members per group
        */
        int customs_count = 0;
        int group_members = 0;
        HashMap<Character, Integer> group_answers = new HashMap<Character, Integer>();

        String curr_line;
        while (input_scan.hasNextLine()) {
            curr_line = input_scan.nextLine();
            
            if (curr_line.length() == 0) {
                int new_adds = 0;
                for (Character question : group_answers.keySet()) {
                    if (group_answers.get(question) == group_members) {
                        new_adds++;
                    }
                }
                customs_count += new_adds;
                group_answers.clear();
                group_members = 0;
            } else {
                group_members++;
                char[] curr_answers = curr_line.toCharArray();
                for (Character question : curr_answers) {
                    if (group_answers.containsKey(question)) {
                        group_answers.put(question, group_answers.get(question)+1);
                    } else {
                        group_answers.put(question, 1);
                    }
                }
            }   
        }

        // add what we have at the end
        int new_adds = 0;
        for (Character question : group_answers.keySet()) {
            if (group_answers.get(question) == group_members) {
                new_adds++;
            }
        }
        customs_count += new_adds;
        group_answers.clear();
        group_members = 0;


        return customs_count;
    }

    public static void main(String[] args) throws Exception {
        String file_name = "./Day5/day5_input.txt";

        try {
            Scanner input_scan = new Scanner(new File(file_name));
            System.out.println("Customs count: " + customCustoms(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}