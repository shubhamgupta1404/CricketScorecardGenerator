package Models;

public enum TypeOfBalls {
    DOT_BALL("0"), WIDEBALL("Wd"), NO_BALL("Nb"), WICKET("W"),
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6");

    public String value;
    TypeOfBalls(String value) {
        this.value = value;
    }
}