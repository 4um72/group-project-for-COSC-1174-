package OddsOfPlay;

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

		case 6:
			odds = 99.78;
		case 5:
			odds = 92.28;
		case 4:
			odds = 85.25;
		case 3:
			odds = 72.22;
		case 2:
			odds = 55.56;
		case 1:
			odds = 33.34;

		}
		return odds;

	}

	public double oddsOfRollingFarkle(int diceLeft) {
		double odds = 100.00;

		switch (diceLeft) {

		case 6:
			odds = 2.52;
		case 5:
			odds = 7.72;
		case 4:
			odds = 15.74;
		case 3:
			odds = 27.78;
		case 2:
			odds = 44.44;
		case 1:
			odds = 66.67;

		}
		return odds;
	}

	@Override
	public String toString() {
		return "You have " + diceLeft + " your odds of scoring are: " + oddsOfScoring(diceLeft)
				+ "% and the odds of rollling a farkle with " + diceLeft + " will be: " + oddsOfRollingFarkle(diceLeft)
				+ "%";
	}
}
