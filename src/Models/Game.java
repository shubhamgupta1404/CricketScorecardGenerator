package Models;

public class Game {
    private final int playersCount;
    private final int oversCount;

    private Team team1;
    private Team team2;
    private Team currentTeam = null;

    public Game(int playersCount, int oversCount) {
        this.playersCount = playersCount;
        this.oversCount = oversCount;
        this.team1 = new Team(playersCount);
        this.team2 = new Team(playersCount);
    }

    public Team getCurrentTeam() {
        return this.currentTeam;
    }

    public void setCurrentTeam(int teamNumber) throws Exception {
        if (teamNumber == 1)
            this.currentTeam = team1;
        else if (teamNumber == 2)
            this.currentTeam = team2;
        else
            throw new Exception("Cricket is a game of 2 teams you silly!!");
    }

    public int getOversCount() {
        return oversCount;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public String calculateWinner() {
        String message;
        if (team1.getScore() > team2.getScore()) {
            message = "Team 1 won the match by " + String.valueOf(team1.getScore() - team2.getScore()) + " runs.";
        } else if (team1.getScore() < team2.getScore()) {
            message = "Team 2 won the match by " + String.valueOf(team2.getScore() - team1.getScore()) + " runs.";
        }else {
            message = "The match was tied.";
        }
        return message;
    }
}
