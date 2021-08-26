package Models;

import java.util.ArrayList;

public class Team {
    private final int playersCount;

    private int score;

    private int wickets;

    private ArrayList<Batsman> players;

    public Team(int playersCount) {
        this.playersCount = playersCount;
        this.score = 0;
        this.wickets = 0;
        players = new ArrayList<>();
    }

    public void addPlayer(String playerName) {
        Batsman p = new Batsman(playerName);
        players.add(p);
    }

    public int getScore() {
        return score;
    }

    public int getWickets() {
        return wickets;
    }

    public boolean isInningsOver() {
        return wickets == playersCount - 1;
    }

    public void setIsPlaying(int playerId) {
        players.get(playerId).setPlaying(true);
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            message.append(players.get(i) + "\n");
        }
        return message.toString();
    }

    public void ballFaced(int player, int runs, int extra, boolean isBoundary, boolean isLegalDelivery, boolean isWicket) {
        players.get(player).ballFaced(runs, isBoundary, isLegalDelivery, isWicket);
        this.score += runs + extra;
        this.wickets += isWicket ? 1 : 0;
    }
}
