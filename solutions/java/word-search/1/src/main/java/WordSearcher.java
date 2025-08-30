import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class WordSearcher {
    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        
        Map<String, Optional<WordLocation>> map = new HashMap<>();
        for (String s: words) {
            // horizontal
            PairsLocation pl = null;
            pl = searchHorizontal(s, grid);
            if (pl != null) { 
                map.put(s, Optional.of(new WordLocation(pl.start, pl.end)));
                continue;
            }
            // horizontal inv
            String sinv = new StringBuilder(s).reverse().toString();
            pl = searchHorizontal(sinv, grid);
            if (pl != null) { 
                map.put(s, Optional.of(new WordLocation(pl.end, pl.start)));
                continue;
            }
            // vertical
            pl = searchVertical(s, grid);
            if (pl != null) { 
                map.put(s, Optional.of(new WordLocation(pl.start, pl.end)));
                continue;
            }
            // vertical inv
            pl = searchVertical(sinv, grid);
            if (pl != null) { 
                map.put(s, Optional.of(new WordLocation(pl.end, pl.start)));
                continue;
            }
            // diagonal 1
            pl = searchDiagonal1(s, grid);
            if (pl != null) { 
                map.put(s, Optional.of(new WordLocation(pl.start, pl.end)));
                continue;
            }
            // diagonal 1 inv
            pl = searchDiagonal1(sinv, grid);
            if (pl != null) { 
                map.put(s, Optional.of(new WordLocation(pl.end, pl.start)));
                continue;
            }
            // diagonal 2
            pl = searchDiagonal2(s, grid);
            if (pl != null) { 
                map.put(s, Optional.of(new WordLocation(pl.start, pl.end)));
                continue;
            }
            //
            pl = searchDiagonal2(sinv, grid);
            if (pl != null) { 
                map.put(s, Optional.of(new WordLocation(pl.end, pl.start)));
                continue;
            }
            map.put(s, Optional.empty());
        }
        return map;
    }

    PairsLocation searchHorizontal(String word, char[][] grid) {
        int wordLength = word.length();
        int gridHeight = grid.length;
        int gridWidth = grid[0].length;
        if (wordLength > gridWidth) { return null; }

        char[] wordChars = word.toCharArray();

        for (int i = 0; i < gridHeight; ++i) {
            for (int j = 0, m = gridWidth - wordLength + 1; j < m; ++j) {
                boolean found = true;
                for (int k = 0; k < wordLength; ++k) {
                    if (wordChars[k] != grid[i][j + k]) { 
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return new PairsLocation(
                        new Pair(j + 1, i + 1), new Pair(j + wordLength, i + 1));
                }
            }
        }
        return null;
    }

    PairsLocation searchVertical(String word, char[][] grid) {
        int wordLength = word.length();
        int gridHeight = grid.length;
        int gridWidth = grid[0].length;
        if (wordLength > gridHeight) { return null; }

        char[] wordChars = word.toCharArray();

        for (int j = 0; j < gridWidth; ++j) {
            for (int i = 0, n = gridHeight - wordLength + 1; i < n; ++i) {
                boolean found = true;
                for (int k = 0; k < wordLength; ++k) {
                    if (wordChars[k] != grid[i + k][j]) { 
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return new PairsLocation(
                        new Pair(j + 1, i + 1), new Pair(j + 1, i + wordLength));
                }
            }
        }
        return null;
    }
    
    PairsLocation searchDiagonal1(String word, char[][] grid) {
        int wordLength = word.length();
        int gridHeight = grid.length;
        int gridWidth = grid[0].length;
        if (wordLength > gridHeight || wordLength > gridWidth) { return null; }

        char[] wordChars = word.toCharArray();
        for (int i = 0, n = gridHeight - wordLength + 1; i < n; ++i) {
            for (int j = 0, m = gridWidth - wordLength + 1; j < m; ++j) {
                boolean found = true;
                for (int k = 0; k < wordLength; ++k) {
                    if (wordChars[k] != grid[i + k][j + k]) { 
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return new PairsLocation(
                        new Pair(j + 1, i + 1), new Pair(j + wordLength, i + wordLength));
                }
            }
        }
        return null;
    }

    PairsLocation searchDiagonal2(String word, char[][] grid) {
        int wordLength = word.length();
        int gridHeight = grid.length;
        int gridWidth = grid[0].length;
        if (wordLength > gridHeight || wordLength > gridWidth) { return null; }

        char[] wordChars = word.toCharArray();
        for (int i = wordLength - 1; i < gridHeight; ++i) {
            for (int j = 0, m = gridWidth - wordLength + 1; j < m; ++j) {
                boolean found = true;
                for (int k = 0; k < wordLength; ++k) {
                    if (wordChars[k] != grid[i - k][j + k]) { 
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return new PairsLocation(
                        new Pair(j + 1, i + 1), new Pair(j + wordLength, i - wordLength + 2));
                }
            }
        }
        return null;
    }

    private class PairsLocation {
        
        public final Pair start;
        public final Pair end;
        
        public PairsLocation(Pair start, Pair end) {
            this.start = start;
            this.end = end;
        }
    }

}
