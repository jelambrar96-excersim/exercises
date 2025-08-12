class Robot {

    private static Orientation [] listOrientation = new Orientation[] {
        Orientation.NORTH, Orientation.EAST, Orientation.SOUTH, Orientation.WEST
    };
    private static int[] advanceX = new int [] { 0, 1, 0, -1 };
    private static int[] advanceY = new int [] { 1, 0, -1, 0 };

    private GridPosition gridPostion;
    private Orientation orientation;


    Robot(GridPosition initialPosition, Orientation initialOrientation) {
        this.gridPostion = initialPosition;
        this.orientation = initialOrientation;
    }

    GridPosition getGridPosition() {
        return this.gridPostion;
    }

    Orientation getOrientation() {
        return this.orientation;
    }

    void advance() {
        this.gridPostion = new GridPosition(
            this.gridPostion.x + advanceX[this.orientation.getValue()],
            this.gridPostion.y + advanceY[this.orientation.getValue()]
        );
    }

    void turnLeft() {
        this.orientation = listOrientation[(this.orientation.getValue() + 3) % 4];
    }

    void turnRight() {
        this.orientation = listOrientation[(this.orientation.getValue() + 1) % 4];
    }

    void simulate(String instructions) {
        for(char c: instructions.toCharArray()) {
            if (c == 'A') { this.advance(); }
            else if (c == 'L') { this.turnLeft(); }
            else if (c == 'R') { this.turnRight(); }
        }
    }

}