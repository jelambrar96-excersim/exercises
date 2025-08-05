import java.util.ArrayList;
import java.util.List;

class Robot {

    private static List<Orientation> listOrientation;
    private static int[] advanceX;
    private static int[] advanceY;

    static {
        advanceX = new int [] { 0, 1, 0, -1 };
        advanceY = new int [] { 1, 0, -1, 0 };
        listOrientation = new ArrayList<Orientation>(4);
        listOrientation.add(Orientation.NORTH);
        listOrientation.add(Orientation.EAST);
        listOrientation.add(Orientation.SOUTH);
        listOrientation.add(Orientation.WEST);
    }

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
            this.gridPostion.x + advanceX[listOrientation.indexOf(this.orientation)],
            this.gridPostion.y + advanceY[listOrientation.indexOf(this.orientation)]
        );
    }

    void turnLeft() {
        this.orientation = listOrientation.get((listOrientation.indexOf(this.orientation) + 3) % 4);
    }

    void turnRight() {
        this.orientation = listOrientation.get((listOrientation.indexOf(this.orientation) + 1) % 4);
    }

    void simulate(String instructions) {
        for(char c: instructions.toCharArray()) {
            if (c == 'A') { this.advance(); }
            else if (c == 'L') { this.turnLeft(); }
            else if (c == 'R') { this.turnRight(); }
        }
    }

}