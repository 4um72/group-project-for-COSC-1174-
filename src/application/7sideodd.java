package application;

public class Odds {
    protected int diceLeft;

    public Odds() {
        this.diceLeft = 6;
    }

    public int getDiceLeft() {
        return diceLeft;
    }

    public void setDiceLeft(int diceLeft) {
        this.diceLeft = diceLeft;
    }

    public double oddsOfScoring(int diceLeft) {
        double odds = 100.00;

        switch (diceLeft) {
            case 7:
                odds = 83.33;
                break;
            case 6:
                odds = 99.78;
                break;
            case 5:
                odds = 92.28;
                break;
            case 4:
                odds = 85.25;
                break;
            case 3:
                odds = 72.22;
                break;
            case 2:
                odds = 55.56;
                break;
            case 1:
                odds = 33.34;
                break;
        }
        return odds;

    }

    public double oddsOfRollingFarkle(int diceLeft) {
        double odds = 100.00;

        switch (diceLeft) {
            case 7:
                odds = 0.18; 
                break;
            case 6:
                odds = 2.52;
                break;
            case 5:
                odds = 7.72;
                break;
            case 4:
                odds = 15.74;
                break;
            case 3:
                odds = 27.78;
                break;
            case 2:
                odds = 44.44;
                break;
            case 1:
                odds = 66.67;
                break;
        }
        return odds;
    }e

    @Override
    public String toString() {
        return "You have " + diceLeft + " dice. Your odds of scoring are: " + oddsOfScoring(diceLeft)
                + "% and the odds of rolling a Farkle with " + diceLeft + " dice will be: " + oddsOfRollingFarkle(diceLeft)
                + "%";
    }
}
