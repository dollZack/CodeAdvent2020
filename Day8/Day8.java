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

    /**
     * It seems a single instruction must be changed from jmp to nop, or from nop to jmp.
     * Making the correct change will remove the infinite loop in the instructions!
     * Find the instruction to change, and return the value of accumulator after execution of
     * the final instruction.
     * @param input_scan
     * @return
     */
    private static int instructionCorrector(Scanner input_scan) {
        /**
         * We need to parse the instructions just like last time,
         * here returning once we've executed the very last instruction.
         * 
         * As we iterate through the instructions, we can try adjust
         * a found jmp/nop, and then try to execute until the final instruction.
         * Like last time, we would know that we're in a loop by executing an instruction
         * we've already seen before.
         * So, if we iterate through, and find an instruction, we can change it, and then
         * run this version of the instructions through a driver, which maintains history
         * of instructions, and returns on duplicate (-1), or on final instruction (accum)
         */
        // parse file into arraylist
        ArrayList<String> instruction_set = new ArrayList<String>();
        while (input_scan.hasNextLine()) {
            instruction_set.add(input_scan.nextLine());
        }

        int accumulator = -1; //return value
        int instruction_index = 0;
        while (accumulator < 0 && instruction_index < instruction_set.size()) {
            String curr_instruction = instruction_set.get(instruction_index);
            String[] instruction_array = curr_instruction.split(" ", 2);

            if (instruction_array[0].equals("nop") || instruction_array[0].equals("jmp")) {
                ArrayList<String> clone_set = (ArrayList<String>)instruction_set.clone();
                instruction_array[0] = instruction_array[0].equals("nop") ? "jmp" : "nop";
                clone_set.set(instruction_index, instruction_array[0] + " " + instruction_array[1]);

                accumulator = correctorDriver(clone_set);
            }

            instruction_index++;
        }

        if (instruction_index == instruction_set.size()) {
            System.out.println("ERROR: REACHED END OF INSTRUCTION SET");
        }

        return accumulator;
    }


    private static int correctorDriver(ArrayList<String> instructions) {
        int accumulator = 0;
        int index = 0;
        HashSet<Integer> past_instructions = new HashSet<Integer>();

        while (!past_instructions.contains(index) && !(index == instructions.size())) {
            past_instructions.add(index);

            String[] instruction_arr = instructions.get(index).split(" ");
            // acc
            if (instruction_arr[0].equals("acc")) {
                int delta = Integer.valueOf(instruction_arr[1].substring(1));
                if (instruction_arr[1].charAt(0) == '+') {
                    accumulator+=delta;
                } else {
                    accumulator-=delta;
                }

                index++;
            // jmp
            } else if (instruction_arr[0].equals("jmp")) {
                int delta = Integer.valueOf(instruction_arr[1].substring(1));
                if (instruction_arr[1].charAt(0) == '+') {
                    index+=delta;
                } else {
                    index-=delta;
                }
            // nop
            } else {
                index++;
            }
        }


        if (index == instructions.size()) {
            return accumulator;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        String file_path = "./Day8/day8_input.txt";
        // String file_path = "./Day8/test_input.txt";

        try {
            Scanner input_scan = new Scanner(new File(file_path)); 
            // System.out.println("Accumulator before loop: " + handheldHalting(input_scan));
            System.out.println("Accumulator after final instruction: " + instructionCorrector(input_scan));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
