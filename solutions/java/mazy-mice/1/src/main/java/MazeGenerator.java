import java.util.Random;
import java.awt.Point;

public class MazeGenerator {

    private final static char EMPTY_CELL = ' '; 
    private final static char WALL_CELL = 'X'; 

    public char[][] generatePerfectMaze(int rows, int columns) {
        return generatePerfectMaze(rows, columns, null);
    }

    public char[][] generatePerfectMaze(int rows, int columns, int seed) {
        return generatePerfectMaze(rows, columns, Integer.valueOf(seed));
    }

    private char[][] generatePerfectMaze(int rows, int columns, Integer seed) {

        if (rows <= 0 || rows > 100) { throw new IllegalArgumentException(); }
        if (columns <= 0 || columns > 100) { throw new IllegalArgumentException(); }

        char[][] board = new char[2 * rows + 1][2 * columns + 1];
        for (int i = 0, n = 2 * rows + 1; i < n; ++i)
            for (int j = 0, m = 2 * columns + 1; j < m; ++j)
                board[i][j] = WALL_CELL;

        Random rd = new Random();
        if (seed != null) {
            rd.setSeed(seed);
        }

        Point start = new Point(0, rd.nextInt(rows));
        Point end = new Point(columns - 1, rd.nextInt(rows));

        MazeTree mazeTree = new DFSMaze(rows, columns, start, end, seed).generate();
        fillEmpty(mazeTree, board);
        char[][] new_board = fillBorders(board);

        new_board[2 * start.y + 1][0] = '⇨';
        new_board[2 * end.y + 1][2 * columns] = '⇨';
        
        // displayMaze(board);
        // displayMaze(new_board);
        
        return new_board;
    }


    private void fillEmpty(MazeTree tree, char[][] board) {
        Point currePoint = tree.point;
        Point currentBoardPosition = new Point(
            2 * currePoint.x + 1,
            2 * currePoint.y + 1);
        board[currentBoardPosition.y][currentBoardPosition.x] = EMPTY_CELL;
        for (int i = 0, n = tree.childrePoints.size(); i < n; ++i) {
            int diffx = tree.childrePoints.get(i).point.x - currePoint.x;
            int diffy = tree.childrePoints.get(i).point.y - currePoint.y;
            board[currentBoardPosition.y + diffy][currentBoardPosition.x + diffx] = EMPTY_CELL;
            board[currentBoardPosition.y + 2 * diffy][currentBoardPosition.x + 2 * diffx] = EMPTY_CELL;
            fillEmpty(tree.childrePoints.get(i), board);
        }
    }

    private char[][] fillBorders(char[][] board) {
        char[][] new_board = new char[board.length][board[0].length];
        // fill corners
        new_board[0][0] = '┌';
        new_board[0][board[0].length - 1] = '┐';
        new_board[board.length - 1][0] = '└';
        new_board[board.length - 1][board[0].length - 1] = '┘';
        // fill top and down border
        for (int i = 1, n = board[0].length - 1; i < n; ++i) {
            new_board[0][i] = board[1][i] == EMPTY_CELL ? '─' : '┬';
            new_board[board.length - 1][i] = board[board.length - 2][i] == EMPTY_CELL ? '─' : '┴';
        }
        // fill right and left border
        for (int i = 1, n = board.length - 1; i < n; ++i) {
            new_board[i][0] = board[i][1] == EMPTY_CELL ? '│' : '├';
            new_board[i][board[0].length - 1] = board[i][board[0].length - 2] == EMPTY_CELL ? '│' : '┤';
        }
        // fill internal
        for (int i = 1, n = board.length - 1; i < n; ++i) {
            for (int j = 1, m = board[0].length - 1; j < m; ++j) {
                if (board[i][j] == EMPTY_CELL) {
                    new_board[i][j] = EMPTY_CELL;
                    continue;
                }
                String wallString = String.copyValueOf(new char[] {
                    board[i][j + 1], board[i - 1][j], board[i][j - 1], board[i + 1][j]
                });
                new_board[i][j] = switch(wallString) {
                    case "XXXX" -> '┼';

                    case " XXX" -> '┤';
                    case "X XX" -> '┬';
                    case "XX X" -> '├';
                    case "XXX " -> '┴';
                    
                    case " X X" -> '│';
                    case "   X" -> '┬';
                    case " X  " -> '┴';
                    
                    case "X X " -> '─';
                    case "  X " -> '┤';
                    case "X   " -> '├';
                    
                    case "XX  " -> '└';
                    case " XX " -> '┘';
                    case "  XX" -> '┐';
                    case "X  X" -> '┌';
                    case "    " -> '┼';

                    default -> EMPTY_CELL;
                };
            }
        }
        return new_board;
    }

    @SuppressWarnings("unused")
    private void displayMaze(char[][] board) {
        for (char[] row: board) {
            System.out.println(String.copyValueOf(row));
        }
    }

}
