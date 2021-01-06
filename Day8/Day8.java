package Day8;

import java.io.File;
import java.util.ArrayList;
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
        int acculumator = 0;

        // parse file into arraylist
        ArrayList<String> instruction_set = new ArrayList<String>();
        while (input_scan.hasNextLine()) {
            instruction_set.add(input_scan.nextLine());
        }

        

        return acculumator;
    }


    public static void main(String[] args) {
        String file_path = "./Day8/day8_input.txt";
        try {
            Scanner input_scan = new Scanner(new File(file_path)); 
            handheldHalting(input_scan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
