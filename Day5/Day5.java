package Day5;

import java.io.File;
import java.util.Scanner;

public class Day5 {
    

    public static void main(String[] args) throws Exception {
        String file_path = "./Day5/day5_input.txt";

        try {
            Scanner input_scan = new Scanner(new File(file_path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}