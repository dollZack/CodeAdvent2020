package RainRisk;

public class Waypoint extends Location {
    private Location ship_location;

    public Waypoint() {
        setNorth(1);
        setEast(10);
        setSouth(0);
        setWest(0);
        ship_location = new Location();
    }

    public Location getLocation() {
        return this.ship_location;
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
                this.rotateLeft(instruction, value);
                break;
            case 'R':
                this.rotateRight(instruction, value);
                break;
            case 'F':
                for (int i = 0; i < value; i++) {
                    if (this.getNorth() != 0) {
                        this.ship_location.move('N', this.getNorth());
                    }

                    if (this.getEast() != 0) {
                        this.ship_location.move('E', this.getEast());
                    }

                    if (this.getSouth() != 0) {
                        this.ship_location.move('S', this.getSouth());
                    }

                    if (this.getWest() != 0) {
                        this.ship_location.move('W', this.getWest());
                    }
                }
                break;
            default:
                System.out.println("UNEXPECTED INSTRUCTION IN MOVE");
                break;
        }
    }


    private void rotateLeft(char instruction, int value) {
        // System.out.println("\ncurrent dir in rotate: " + dir.getDir());
        // System.out.println("rotation direction: " + instruction);
        // System.out.println("rotation value: " + value);
        while (value > 0) {
            int n = this.getNorth();
            int w = this.getWest();
            int s = this.getSouth();
            int e = this.getEast();
            this.setWest(n);
            this.setSouth(w);
            this.setEast(s);
            this.setNorth(e);
            value-=90;
        }
    }

    private void rotateRight(char instruction, int value) {
        while (value > 0) {
            int n = this.getNorth();
            int e = this.getEast();
            int s = this.getSouth();
            int w = this.getWest();
            this.setEast(n);
            this.setSouth(e);
            this.setWest(s);
            this.setNorth(w);
            value-=90;
        }
    }
}
