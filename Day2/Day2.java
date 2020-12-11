package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static int ValidPasswords(Scanner input_scan) {
        int num_valid = 0;


        return num_valid;
    }

    public static void main(String[] args) throws FileNotFoundException {
    
        try {
            Scanner input_scan = new Scanner(new File(args[0]));

        } catch (Exception e) {
            System.out.println("Problem opening file: ");
            e.printStackTrace();
        }
    }
}
