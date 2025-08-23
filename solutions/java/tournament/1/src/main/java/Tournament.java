import java.util.HashMap;

class Tournament {

    private HashMap<String, Team> map = new HashMap<>();

    String printTable() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Team                           | MP |  W |  D |  L |  P\n");
        for (Team s: this.map.values().stream().sorted().toList()) {
            sBuilder.append(s.toString() + "\n");
        }
        return sBuilder.toString();
    }

    void applyResults(String resultString) {
        String[] rows = resultString.split("\n");
        for (String r: rows) {
            String [] cols = r.split(";");
            String team0 = cols[0], team1 = cols[1], result = cols[2];
            if (!this.map.containsKey(team0)) { this.map.put(team0, new Team(team0)); }
            if (!this.map.containsKey(team1)) { this.map.put(team1, new Team(team1)); }
            if (result.equals("win")) {
                this.map.get(team0).win();
                this.map.get(team1).lost();
            }
            else if (result.equals("loss")) {
                this.map.get(team0).lost();
                this.map.get(team1).win();
            }
            else if (result.equals("draw")) {
                this.map.get(team0).draw();
                this.map.get(team1).draw();
            }
        }
    }


    private class Team implements Comparable<Team>{
        
        final String name;
        public int points;
        public int winGames;
        public int lostGames;
        public int drawGames;
        public int playedGames;

        public Team(String name) {
            this.name = name;
            this.points = 0;
            this.winGames = 0;
            this.lostGames = 0;
            this.drawGames = 0;
            this.playedGames = 0;
        }

        public void win() {
            this.points += 3;
            this.playedGames++;
            this.winGames++;
        }

        public void draw() {
            this.points += 1;
            this.drawGames++;
            this.playedGames++;
        }

        public void lost() {
            this.lostGames++;
            this.playedGames++;
        }

        @Override
        public String toString() {
            return String.format(
                "%-30s | %2d | %2d | %2d | %2d | %2d",
                name,           // %-30s → alineado a la izquierda, 30 caracteres
                playedGames,    // %2d → 2 dígitos alineados a la derecha
                winGames,
                drawGames,
                lostGames,
                points
            );
        }

        @Override
        public int compareTo(Team t) {
            int diff = t.points - this.points;
            if (diff != 0) { return diff; }
            return this.name.compareTo(t.name);
        }

    }
}
