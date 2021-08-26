package Models;

public class Batsman extends Player {
    private int score;
    private int fours;
    private int sixes;
    private int balls;

    private boolean isPlaying;


    public Batsman(String name) {
        super(name);
        this.score = 0;
        this.fours = 0;
        this.sixes = 0;
        this.balls = 0;
        this.isPlaying = false;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public void ballFaced(int runScored, boolean isBoundary, boolean isLegalDelivery, boolean isWicket) {
        score += runScored;
        if (runScored == 4 && isBoundary) {
            fours += 1;
        }
        if (runScored == 6 && isBoundary) {
            sixes += 1;
        }
        balls += isLegalDelivery ? 1 : 0;
        setPlaying(!isWicket);
    }

    @Override
    public String toString() {
        return getName() + (isPlaying ? "*" : "") + "\t\t\t\t" +
                score + "\t\t" +
                fours + "\t\t" +
                sixes + "\t\t" +
                balls + "\t\t";
    }
}
