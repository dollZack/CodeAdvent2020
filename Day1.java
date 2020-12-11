import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    private static void ReportRepair(Scanner input_scan) {

    }

    public static void main(String[] args) throws FileNotFoundException {

        try {
            Scanner input_scan = new Scanner(new File(args[0]));
            ReportRepair(input_scan);
        } catch (Exception e) {
            System.out.println("Error opening file:");
            e.printStackTrace();
            System.exit(0);
        }
        
    }
}
