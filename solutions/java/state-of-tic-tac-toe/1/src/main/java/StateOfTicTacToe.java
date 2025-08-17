class StateOfTicTacToe {

    public boolean isWin(String[] board, char Symbol) {
        // rows
        String expectedString = new String(new char[]{Symbol, Symbol, Symbol});
        for (String row: board) { 
            if (row.equals(expectedString)) { return true; }
        }
        // cols
        boolean flag;
        for (int j = 0; j < 3; ++j) {
            flag = true;
            for (int i = 0; i < 3; ++i) {
                if (board[i].charAt(j) != Symbol) {
                    flag = false;
                    break;
                }
            }
            if (flag) { return true; }
        }
        // d1
        flag = true;
        for (int i = 0; i < 3; ++i) {
            if (board[i].charAt(i) != Symbol) {
                flag = false;
                break;
            }
        }
        if (flag) { return true; }
        // d2
        flag = true;
        for (int i = 0; i < 3; ++i) {
            if (board[3 - i - 1].charAt(i) != Symbol) {
                flag = false;
                break;
            }
        }
        if (flag) { return true; }
        return false;
    }


    public GameState determineState(String[] board) {
        
        int xCounter = 0, oCounter = 0;
        for (String row: board) {
            for(char c: row.toCharArray()) {
                if (c == 'X') xCounter += 1;
                if (c == 'O') oCounter += 1;
            }
        }
        if (oCounter > xCounter) { throw new IllegalArgumentException("Wrong turn order: O started"); }
        if (xCounter > (oCounter + 1)) { throw new IllegalArgumentException("Wrong turn order: X went twice"); }

        // rows
        boolean xWin = isWin(board, 'X'); 
        boolean oWin = isWin(board, 'O'); 
        if (xWin && oWin) { 
            throw new IllegalArgumentException("Impossible board: game should have ended after the game was won");
        }
        if (xWin || oWin)return GameState.WIN; 

        boolean flag = true;
        for (int i = 0; i < 3; ++i) {
            if (board[i].contains(" ")) {
                flag = false;
                break;
            }
        }
        return flag ? GameState.DRAW: GameState.ONGOING;
    }
}
