package Controller;

import Models.Game;
import Models.TypeOfBalls;

import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {

    private final int MAX_BALLS_IN_OVER = 6;

    private static GameEngine ge = null;

    public static GameEngine getInstance() {
        if (ge == null)
            ge = new GameEngine();

        return ge;
    }

    /**
     * Starts a new Cricket Match
     * @throws Exception
     */
    public void startGame() throws Exception {
        Scanner sc = new Scanner(System.in);

        int playersCount;
        while (true) {
            System.out.println("No. of players for each team: ");
            try {
                playersCount = Integer.parseInt(sc.next());
                if (playersCount < 2) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid number of players greater than 0");
            }
        }

        int overs;
        while (true) {
            System.out.println("No. of overs: ");
            try {
                overs = Integer.parseInt(sc.next());
                if (overs <= 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid number of overs greater than 0");
            }
        }

        Game game = new Game(playersCount, overs);

        startInnings(sc, game, 1);

        startInnings(sc, game, 2);

        displayWinner(game);
    }

    /**
     *
     * @param sc Scanner object
     * @param game A cricket Match object
     * @param innings innings number
     * @throws Exception
     */
    private void startInnings(Scanner sc, Game game, int innings) throws Exception {
        game.setCurrentTeam(innings);

        System.out.println("Batting Order for team " + innings + ":\n");

        int counter = game.getPlayersCount();

        while (counter-- > 0) {
            String playerName = sc.next();
            game.getCurrentTeam().addPlayer(playerName);
        }

        ArrayList<Integer> onCreasePlayers = new ArrayList<>();
        onCreasePlayers.add(++counter);
        onCreasePlayers.add(++counter);

        int onStrikePlayer = 0;
        for (int over = 1; over <= game.getOversCount(); over++) {
            int balls = 0;
            System.out.println("Over " + over + ":");
            while (balls < MAX_BALLS_IN_OVER && !game.getCurrentTeam().isInningsOver()) {
                String ballResult = sc.next();
                TypeOfBalls typeOfBall = TypeOfBalls.valueOf(ballResult);
                int runs;
                int extra = 0;
                boolean isLegalDelivery = true;
                boolean isWicket = false;
                boolean switchSide = false;
                try {
                    switch (typeOfBall) {
                        case DOT_BALL -> runs = 0;
                        case ONE -> {
                            runs = 1;
                            switchSide = true;
                        }
                        case TWO -> runs = 2;
                        case THREE -> {
                            runs = 3;
                            switchSide = true;
                        }
                        case FOUR -> runs = 4;
                        case SIX -> runs = 6;
                        case WICKET -> {
                            runs = 0;
                            isWicket = true;
                        }
                        case WIDEBALL -> {
                            runs = 0;
                            extra = 1;
                            isLegalDelivery = false;
                        }
                        case NO_BALL -> {
                            runs = 0;
                            extra = 1;
                        }
                        default -> throw new Exception("Invalid result of ball.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " Try again!!");
                    continue;
                }
                game.getCurrentTeam().ballFaced(onCreasePlayers.get(onStrikePlayer), runs, extra, isLegalDelivery, isWicket);
                if (isWicket && !game.getCurrentTeam().isInningsOver()) {
                    onCreasePlayers.set(onStrikePlayer, ++counter);
                }
                if (isLegalDelivery) balls++;
                if (switchSide) {
                    onStrikePlayer = onStrikePlayer == 1 ? 0 : 1;
                }
            }
            displayOverSummary(innings, game, over, balls);
            onStrikePlayer = onStrikePlayer == 1 ? 0 : 1;
        }
    }

    private void displayOverSummary(int innings, Game game, int over, int balls) {
        System.out.println("Scorecard for Team " + innings + ":");
        System.out.println("Player Name\t\tScore\t4s\t\t6s\t\tBalls");
        System.out.println(game.getCurrentTeam());
        System.out.println("Total: " + game.getCurrentTeam().getScore() + "/" + game.getCurrentTeam().getWickets());
        System.out.println("Overs: " + (balls == 6 ? over : (over - 1) + "." + balls));
        System.out.println();
    }

    private void displayWinner(Game game) {
        System.out.println("Result: " + game.calculateWinner());
    }
}
