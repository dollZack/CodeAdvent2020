package Day5;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class Day5 {
    
    /**
     * Everybody needs help answering their customs questions! The file pointed to by the given 
     * scanner contains groups of people and the questions to which they answered yes, where a given
     * line is a persons' answers to which they answered yes, and everygroup is seperated by an empty
     * line.
     * 
     * @param input_scan
     * @return The sum of the number of unique questions answered yes by each group
     */
    private static int customCustoms(Scanner input_scan) {
        /* 
        basically can create a hashset for every group,
        */
        int customs_count = 0;
        HashSet<Character> group_answers = new HashSet<Character>();

        String curr_line;
        while (input_scan.hasNextLine()) {
            curr_line = input_scan.nextLine();
            
            if (curr_line.length() == 0) {
                customs_count += group_answers.size();
                group_answers.clear();
            } else {
                char[] curr_answers = curr_line.toCharArray();
                for (Character question : curr_answers) {
                    group_answers.add(question);
                }
            }   
        }

        // add what we have at the end
        customs_count += group_answers.size();
        group_answers.clear();


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