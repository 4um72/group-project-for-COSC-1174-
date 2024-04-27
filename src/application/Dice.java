package application;

public class Dice {

	int faceValue;

	
	public Dice() {
		this.faceValue = (int)(Math.random()* 6) + 1;
	}
	
	public Dice(int faceValue) {
		this.faceValue = faceValue;
	}
	
	public int getFaceValue() {
		return faceValue;
	}
}
