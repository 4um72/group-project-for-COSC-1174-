package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root pane
        VBox root = new VBox();

        HeaderPane headerPane = new HeaderPane();
        ScorePane computerScorePane = new ScorePane("Computer\nScore");
        DicePane computerBankedDicePane = new DicePane("Computer\nBanked\nDice");
        DicePane playerBankedDicePane = new DicePane("Player\nBanked\nDice");
        ScorePane playerScorePane = new ScorePane("Player\nScore");
        DiceAreaPane diceAreaPane = new DiceAreaPane(playerBankedDicePane, computerBankedDicePane, playerScorePane);
        
        root.getChildren().addAll(headerPane, computerScorePane, computerBankedDicePane, diceAreaPane, playerBankedDicePane, playerScorePane);

        // Set scene and stage
        Scene scene = new Scene(root, 480, 640);
        //primaryStage.setTitle("Farkle Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// HeaderPane class
class HeaderPane extends StackPane {
    public HeaderPane() {
        setPrefHeight(60);
        setAlignment(Pos.CENTER);

        Label title = new Label("Farkle");
        title.setFont(Font.font(30));
        title.setTranslateX(-60);

        Text subTitle = new Text("Created by\nGroup 4");
        subTitle.setTranslateX(5);
        subTitle.setTranslateY(10);
        subTitle.setFont(Font.font(10));

        TextField playerNameInput = new TextField();
        playerNameInput.setPromptText("Player Name");
        playerNameInput.setMaxWidth(82);
        playerNameInput.setMaxHeight(30);
        playerNameInput.setTranslateX(115);
        playerNameInput.setTranslateY(-15);

        Button newGameButton = new Button("New Game");
        newGameButton.setTranslateX(115);
        newGameButton.setTranslateY(15);
        newGameButton.setFont(Font.font(12));
        newGameButton.setMaxSize(82, 30);

        Button sevenDiceOption = new Button("7-Sided\nDice Option");
        sevenDiceOption.setMaxSize(82, 30);
        sevenDiceOption.setTranslateX(198);
        sevenDiceOption.setTranslateY(15);
        sevenDiceOption.setFont(Font.font(9));

        Label winsLabel = new Label("Wins: 0");
        winsLabel.setTranslateX(198);
        winsLabel.setTranslateY(-23);
        winsLabel.setFont(Font.font(12));

        Label lossesLabel = new Label("Losses: 0");
        lossesLabel.setTranslateX(198);
        lossesLabel.setTranslateY(-10);
        lossesLabel.setFont(Font.font(12));

        getChildren().addAll(title, playerNameInput, newGameButton, subTitle, sevenDiceOption, winsLabel, lossesLabel);
    }
}

// ScorePane class
class ScorePane extends StackPane {
    private Label scoreValue;  // Make scoreValue an instance variable

    public ScorePane(String label) {
        setPrefHeight(60);
        Label scoreLabel = new Label(label);
        scoreLabel.setFont(Font.font(16));
        scoreLabel.setTranslateX(-15);
        scoreValue = new Label("0");  // Initialize scoreValue here
        scoreValue.setFont(Font.font(26));
        scoreValue.setTranslateX(40);
        getChildren().addAll(scoreLabel, scoreValue);
    }

    // Define setScore outside of the ScorePane constructor
    public void setScore(int score) {
        scoreValue.setText(String.valueOf(score));
    }
}


// DicePane class
class DicePane extends StackPane {
    private HBox diceBox;

    public DicePane(String label) {
        setPrefHeight(60);
        Label diceLabel = new Label(label);
        diceLabel.setTranslateX(-200);

        diceBox = new HBox(10);
        diceBox.setAlignment(Pos.CENTER);
        diceBox.setTranslateX(20);

        getChildren().addAll(diceLabel, diceBox);
    }

    public void addDice(ImageView dice) {
        diceBox.getChildren().add(dice);
    }

    public void removeDice(ImageView dice) {
        diceBox.getChildren().remove(dice);
    }

    public void removeAllClickEvents() {
        for (Node dice : diceBox.getChildren()) {
            ((ImageView) dice).setOnMouseClicked(null);
        }
    } 
}


