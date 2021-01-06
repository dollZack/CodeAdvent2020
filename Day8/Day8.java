package Day8;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day8 {
    /**
     * handheldHalting
     * 
     * 
     * @param args
     */
    private static int handheldHalting(Scanner input_scan) {
        /* 
        we're given boot instructions of the form <instruction> <value>,
        where the instruction can be 'acc', 'jmp', or 'nop'.
        'acc' updates an accumlutor variable.
        'jmp' moves the instruction pointer
        'nop' does nothing

        We need to recognize when we're executing an instruction for the second
        time -- indicating in this problem an infinite loop -- and return the
        value of acculumator just before that instruction would execute.
        */

        /* 
        there exist duplicates of instructions in the file, the reading the instruction
        itself isn't valuable.

        However, because we're jumping around, we need to be able to index into this
        series of instructions.

        So let's store the instructions in an arraylist, and then as we execute instructions,
        add them to a hashset. if ever we encounter an instruction index which we've already
        seen, this is the infinite loop described.
        */
        int accumulator = 0;

        // parse file into arraylist
        ArrayList<String> instruction_set = new ArrayList<String>();
        while (input_scan.hasNextLine()) {
            instruction_set.add(input_scan.nextLine());
        }

        // Create history of instructions
        HashSet<Integer> past_instructions = new HashSet<Integer>();
        int instruction = 0;

        String[] instruction_arr; // used for parsing

        // Execute
        while (instruction < instruction_set.size() && !past_instructions.contains(instruction)) {
            past_instructions.add(instruction);

            instruction_arr = instruction_set.get(instruction).split(" ");
            // acc
            if (instruction_arr[0].equals("acc")) {
                int delta = Integer.valueOf(instruction_arr[1].substring(1));
                if (instruction_arr[1].charAt(0) == '+') {
                    accumulator+=delta;
                } else {
                    accumulator-=delta;
                }

                instruction++;
            // jmp
            } else if (instruction_arr[0].equals("jmp")) {
                int delta = Integer.valueOf(instruction_arr[1].substring(1));
                if (instruction_arr[1].charAt(0) == '+') {
                    instruction+=delta;
                } else {
                    instruction-=delta;
                }
            // nop
            } else {
                instruction++;
            }
        }

        // exit once we've seen duplicate instruction 

        return accumulator;
    }


    public static void main(String[] args) {
        String file_path = "./Day8/day8_input.txt";
        try {
            Scanner input_scan = new Scanner(new File(file_path)); 
            System.out.println("Accumulator before loop: " + handheldHalting(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
