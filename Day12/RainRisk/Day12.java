package RainRisk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12 {
    
    /**
     * We've got to do some navigation! Given an input file of directions in the form of
     * <Letter><Integer>, we must follow the directions, and then compute the manhattan
     * distance from the starting location.
     * 
     * The possible letters are NSEW (directions), L(eft) R(ight), relative, and F(orward).
     * The integers after L and R are degrees to rotate (factors of 90), and the integers 
     * after the other letters depict number of units to move in that direction.
     * 
     * The manhattan
     * @param input_scan
     */
    private static Location steer(Scanner input_scan, Location directions) {
        while(input_scan.hasNextLine()) {
            String curr_line = input_scan.nextLine();
            char instruction = curr_line.charAt(0);
            int value = Integer.valueOf(curr_line.substring(1));
            directions.move(instruction, value);
            // System.out.println(directions.toString());
        }

        return directions;
    }


    private static Location waypointSteer(Scanner input_scan, Waypoint point) {
        while(input_scan.hasNextLine()) {
            String curr_line = input_scan.nextLine();
            char instruction = curr_line.charAt(0);
            int value = Integer.valueOf(curr_line.substring(1));
            point.move(instruction, value);
        }

        return point.getLocation();
    }

    // private static void move(Direction dir, char instruction, int value) {
    //     switch (instruction) {
    //         case 'N':
    //             dir.setNorth(dir.getNorth()+value);
    //             break;
    //         case 'E':
    //             dir.setEast(dir.getEast() + value);
    //             break;
    //         case 'S':
    //             dir.setSouth(dir.getSouth() + value);
    //             break;
    //         case 'W':
    //             dir.setWest(dir.getWest() + value);
    //             break;
    //         case 'L':
    //             rotate(dir, instruction, value);
    //             break;
    //         case 'R':
    //             rotate(dir, instruction, value);
    //             break;
    //         case 'F':
    //             move(dir, dir.getDir(), value);
    //             break;
    //         default:
    //             System.out.println("UNEXPECTED INSTRUCTION IN MOVE");
    //             break;
    //     }
    // }

    // private static void rotate(Direction dir, char instruction, int value) {
    //     // System.out.println("\ncurrent dir in rotate: " + dir.getDir());
    //     // System.out.println("rotation direction: " + instruction);
    //     // System.out.println("rotation value: " + value);

    //     char[] directions = {'N', 'E', 'S', 'W'};
    //     int dir_index = 0;
    //     while (directions[dir_index] != dir.getDir()) {
    //         dir_index++;
    //     }
    //     // System.out.println("dir_index: " + dir_index);

    //     int dir_diff = value/90;
    //     // System.out.println("dir_diff: " + dir_diff);
    //     char new_dir = 'X';
    //     if (instruction == 'L') {
    //         int new_index = dir_index-dir_diff;
    //         if (new_index < 0) new_index = 4+new_index;
    //         new_dir = directions[new_index];
    //     } else if (instruction == 'R') {
    //         new_dir = directions[(dir_index+dir_diff)%4];
    //     } else {
    //         System.out.println("UNEXPECTED INSTRUCTION IN ROTATE");
    //     }

    //     // System.out.println("new dir: " + new_dir + "\n");
    //     dir.setDir(new_dir);
    // }

    private static void manhattanDistance(Location destination) {
        int ns = Math.abs(destination.getNorth() - destination.getSouth());
        int we = Math.abs(destination.getWest() - destination.getEast());
        System.out.println("Distance: " + (ns + we));
    }

    public static void main(String[] args) throws FileNotFoundException{
        String fn = "./Day12/day12_input.txt";
        Scanner input_scan = new Scanner(new File(fn));
        // Location destination = steer(input_scan, new Location());
        Location destination = waypointSteer(input_scan, new Waypoint());
        manhattanDistance(destination);
    }
}
