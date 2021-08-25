package Models;

public abstract class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void ballFaced(int runs, boolean isLegalDelivery, boolean isWicket);
}
