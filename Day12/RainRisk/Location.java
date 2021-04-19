package RainRisk;

public class Location {
    private int north;
    private int east;
    private int south;
    private int west;
    private char dir;

    public Location() {
        setNorth(0);
        setEast(0);
        setSouth(0);
        setWest(0);
        setDir('E');
    }

    public char getDir() {
        return dir;
    }

    public void setDir(char dir) {
        this.dir = dir;
    }

    public int getWest() {
        return west;
    }

    public void setWest(int west) {
        this.west = west;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public int getEast() {
        return east;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
    }

    public String toString() {
        String string_value = "";
        
        string_value += "North: " + this.north + "\n";
        string_value += "East : " + this.east + "\n";
        string_value += "South: " + this.south + "\n";
        string_value += "West : " + this.west + "\n";
        string_value += "\nDirection: " + this.dir + "\n";

        return string_value;
    }


    public void move(char instruction, int value) {
        switch (instruction) {
            case 'N':
                this.setNorth(this.getNorth()+value);
                break;
            case 'E':
                this.setEast(this.getEast() + value);
                break;
            case 'S':
                this.setSouth(this.getSouth() + value);
                break;
            case 'W':
                this.setWest(this.getWest() + value);
                break;
            case 'L':
                this.rotate(instruction, value);
                break;
            case 'R':
                this.rotate(instruction, value);
                break;
            case 'F':
                this.move(this.getDir(), value);
                break;
            default:
                System.out.println("UNEXPECTED INSTRUCTION IN MOVE");
                break;
        }
    }


    private void rotate(char instruction, int value) {
        // System.out.println("\ncurrent dir in rotate: " + dir.getDir());
        // System.out.println("rotation direction: " + instruction);
        // System.out.println("rotation value: " + value);

        char[] directions = {'N', 'E', 'S', 'W'};
        int dir_index = 0;
        while (directions[dir_index] != this.getDir()) {
            dir_index++;
        }
        // System.out.println("dir_index: " + dir_index);

        int dir_diff = value/90;
        // System.out.println("dir_diff: " + dir_diff);
        char new_dir = 'X';
        if (instruction == 'L') {
            int new_index = dir_index-dir_diff;
            if (new_index < 0) new_index = 4+new_index;
            new_dir = directions[new_index];
        } else if (instruction == 'R') {
            new_dir = directions[(dir_index+dir_diff)%4];
        } else {
            System.out.println("UNEXPECTED INSTRUCTION IN ROTATE");
        }

        // System.out.println("new dir: " + new_dir + "\n");
        this.setDir(new_dir);
    }
}

