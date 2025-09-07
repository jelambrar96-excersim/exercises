import java.awt.Point;
import java.util.ArrayList;

public class MazeTree {
    public Point point;
    public MazeTree parent;
    public ArrayList<MazeTree> childrePoints;
    public MazeTree(Point p, MazeTree parent){
        this.childrePoints = new ArrayList<MazeTree>(3);
        this.point = p;
        this.parent = parent;
    }
}
