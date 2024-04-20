
public class Player {
String name;
int currentScore = 0;
int currentRoundScore = 0;
int playerNum = 0;
boolean easyAI = false;
boolean hardAI = false;

public Player() {
	
}

public Player(String name, int currentScore, int playerNum) {
	this.name = name;
	this.currentScore = currentScore;
	this.playerNum = playerNum;
	if (playerNum == 1) {
		easyAI = true;
	}
	else if (playerNum == 2) {
		hardAI = true;
	}
}

public int getCurrentScore() {
	return currentScore;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public void setRoundScore(int val) {
	this.currentRoundScore = val;
}

public int getPlayerNum() {
	return playerNum;
}

public void addToRoundScore(int val) {
	this.currentRoundScore = this.currentRoundScore + val;
}

public int getCurrentRoundScore() {
	return currentRoundScore;
}

public void addToScore(int val) {
	this.currentScore = currentScore + val;
}

public boolean getEasyAI() {
	return easyAI;
}

public void setEasyAI(int playerNum) {
	if (playerNum == 1) 
		this.easyAI = true;
}

public boolean getHardAI() {
	return hardAI;
}

public void setHardAI(int playerNum) {
	if (playerNum == 2) 
		this.hardAI = true;
}

}
