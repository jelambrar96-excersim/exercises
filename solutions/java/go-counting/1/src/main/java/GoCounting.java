import java.awt.Point;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

class GoCounting {

    private Player[][] board;
    final private static int[] xDirections = new int[] {1, 0, -1, 0};
    final private static int[] yDirections = new int[] {0, 1, 0, -1};

    private Map<Player, Set<Set<Point>>> territoryCache;

    GoCounting(String board) {
        List<String> rows = Arrays.asList(board.split("\n"));
        boolean allRowsSameLen = rows.stream().mapToInt(s -> s.length()).distinct().count() == 1;
        if (!allRowsSameLen) {
            throw new IllegalArgumentException();
        }
        this.board = rows.stream()
            .map(s -> s.chars()
                .mapToObj(c -> switch (c) {
                    case 'B' -> Player.BLACK;
                    case 'W' -> Player.WHITE;
                    case ' ' -> Player.NONE;
                    default -> throw new IllegalArgumentException();
                })
                .toArray(Player[]::new))
            .toArray(Player[][]::new);
        this.territoryCache = new HashMap<Player, Set<Set<Point>>>();
    }

    boolean isValidPoint(int x, int y) {
        if (x < 0 || x >= this.board[0].length) { return false; }
        if (y < 0 || y >= this.board.length) { return false; }
        return true;
    }

    Player getTerritoryOwner(int x, int y) {
        return getGoAux(x, y).player;
    }

    Set<Point> getTerritory(int x, int y) {
        return getGoAux(x, y).territory;
    }

    GoAux getGoAuxCache(int x, int y) {
        for (Map.Entry<Player, Set<Set<Point>>> entry: this.territoryCache.entrySet()) {
            Player owner = entry.getKey();
            for (Set<Point> territory: entry.getValue()) {
                if (territory.contains(new Point(x, y))) return new GoAux(owner, territory);
            }
        }
        return null;
    }

    private void addNewCacheItem(GoAux goaux) {
        if (!this.territoryCache.containsKey(goaux.player)) {
            this.territoryCache.put(goaux.player, new HashSet<Set<Point>>());
        }
        this.territoryCache.get(goaux.player).add(goaux.territory);
    }

    GoAux getGoAux(int x, int y) {
        if (!isValidPoint(x, y)) throw new IllegalArgumentException("Invalid coordinate");
        GoAux cachePlayer = getGoAuxCache(x, y);
        if (cachePlayer != null) { return cachePlayer; }

        LinkedList<Point> stack = new LinkedList<Point>(Arrays.asList(new Point(x, y)));
        HashSet<Point> territory = new HashSet<Point>();
        HashSet<Player> players = new HashSet<Player>();

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            if (!territory.contains(p) && isValidPoint(p.x, p.y)) {
                Player player = this.board[p.y][p.x];
                if (player == Player.NONE) {
                    territory.add(p);
                    for (int i = 0; i < 4; ++i) {
                        Point newP = new Point(p.x + xDirections[i], p.y + yDirections[i]);
                        stack.add(newP);                        
                    }
                }
                else {
                    players.add(player);
                }
            }
        }

        if (!territory.isEmpty() && players.size() == 1) {
        }
        
        Player outPlayer = !territory.isEmpty() && players.size() == 1 
            ? players.stream().findFirst().get()
            : Player.NONE;
        GoAux goAux = new GoAux(outPlayer, territory);
        addNewCacheItem(goAux);
        return goAux;
    }


    Map<Player, Set<Point>> getTerritories() {
        for (int y = 0; y < board.length; ++y) {
            for (int x = 0; x < board[0].length; ++x) {
                getGoAux(x, y); // fill cache
            }
        }
        List<Player> listPlayers = Arrays.asList(Player.NONE, Player.BLACK, Player.WHITE);
        return listPlayers.stream().collect(
            HashMap::new,
            (map, player) -> map.put(
                player,
                this.territoryCache.containsKey(player) 
                    ? this.territoryCache.get(player).stream()
                        .flatMap(Collection::stream).collect(Collectors.toSet())
                    : new HashSet<>()
            ),
            HashMap::putAll);
    }


    private class GoAux {
        private final Player player;
        private final Set<Point> territory;
        
        public GoAux(Player p, Set<Point> territory) {
            this.player = p;
            this.territory = territory;
        }
    }

}