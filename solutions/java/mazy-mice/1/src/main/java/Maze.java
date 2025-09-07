import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.awt.Point;


public class Maze {
    
    protected final static int [] DIRECTIONS_X = new int[] {1, 0, -1, 0};
    protected final static int [] DIRECTIONS_Y = new int[] {0, 1, 0, -1};
    
    protected Random randomDirection;
    protected Point start, end;
    protected int cols, rows;

    public Maze(int rows, int cols, Point start, Point end, Integer seed) {
        
        this.randomDirection = new Random();
        if (seed != null) {
            this.randomDirection.setSeed(seed);
        }
        this.start = start;
        this.end = end;
        this.cols = cols;
        this.rows = rows;
    }

    protected boolean isValidPoint(Point p) {
        if (p.x < 0 || p.x >= this.cols) return false;
        if (p.y < 0 || p.y >= this.rows) return false;
        return true;
    }
    
}

class DFSMaze extends Maze{
    
    public DFSMaze(int rows, int cols, Point start, Point end, Integer seed) {
        super(rows, cols, start, end, seed);
    }

    public MazeTree generate() {

        // int
        HashSet<Point> pointUsed = new HashSet<Point>();
        // MazeTree tree = new MazeTree(this.start, null);
        // pointUsed.add(start);
        
        // // step 1: create a path from start to end
        // MazeTree currentTree = tree;
        // while (currentTree.point != end) {
        //     final Point currentPoint = currentTree.point;
        //     List<Point> validPoints = IntStream.range(0, 4)
        //             .mapToObj(i -> new Point(
        //                 currentPoint.x + DIRECTIONS_X[i],
        //                 currentPoint.y + DIRECTIONS_Y[i]))
        //             .filter(p -> isValidPoint(p))
        //             .filter(pointUsed::contains)
        //             .toList();
        //     Point randomPoint = validPoints.get(
        //             this.randomDirection.nextInt(0, validPoints.size()));
        //     pointUsed.add(randomPoint);
        //     MazeTree nextTree = new MazeTree(randomPoint, currentTree);
        //     currentTree.childrePoints.add(nextTree);
        //     currentTree = nextTree;
        // }

        // connect all unused points
        // while (currentTree.parent != null) {
        //     MazeTree tempTree = fillMaze(currentTree.point, pointUsed);
        //     for (int i = 0; i < tempTree.childrePoints.size(); ++i) {
        //         tempTree.childrePoints.get(i).parent = currentTree;
        //     }
        //     currentTree.childrePoints.addAll(tempTree.childrePoints);
        //     currentTree = currentTree.parent;
        // }
        MazeTree tree = fillMaze(start, pointUsed);
        return tree;
    }

    // public MazeTree findPath(Point start, Point end, HashSet<Point> pointUsed) {
    //     MazeTree tree = new MazeTree(this.start, null);
    //     pointUsed.add(start);
    //     if (start == end) {
    //         return tree;
    //     }
    //     List<Point> validPoints = IntStream.range(0, 4)
    //                 .mapToObj(i -> new Point(
    //                     start.x + DIRECTIONS_X[i],
    //                     start.y + DIRECTIONS_Y[i]))
    //                 .filter(p -> isValidPoint(p))
    //                 .filter(pointUsed::contains)
    //                 .toList();
    //     if (validPoints.size() == 0) { 
    //         pointUsed.remove(start);
    //         return null;
    //     }
    //     ArrayList<Point> randomPoints = new ArrayList<Point>(validPoints);
    //     Collections.shuffle(randomPoints, this.randomDirection);
    //     for (Point p: randomPoints) {
    //         MazeTree mt = findPath(p, end, pointUsed);
    //         if (mt == null) { continue; }
    //     }
    //     pointUsed.remove(start);        
    //     return null;
    // }
    
    public MazeTree fillMaze(Point start, HashSet<Point> pointUsed) {
        MazeTree tree = new MazeTree(start, null);
        pointUsed.add(start);
        Point currentPoint = tree.point;
        ArrayList<Integer> iList = new ArrayList<Integer>(
                IntStream.range(0, 4).boxed().toList());
        Collections.shuffle(iList, this.randomDirection);
        for (Integer i: iList) {
            Point newPoint = new Point(
                    currentPoint.x + DIRECTIONS_X[i],
                    currentPoint.y + DIRECTIONS_Y[i]);
            if (!isValidPoint(newPoint)) { continue; }
            if (pointUsed.contains(newPoint)) { continue; }
            tree.childrePoints.add(fillMaze(newPoint, pointUsed));
        }
        for (int i = 0; i < tree.childrePoints.size(); ++i) {
            tree.childrePoints.get(i).parent = tree;
        }
        return tree;
    }

}