package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        DiceAreaPane diceAreaPane = new DiceAreaPane(playerBankedDicePane, computerBankedDicePane);
        ScorePane playerScorePane = new ScorePane("Player\nScore");

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
    public ScorePane(String label) {
        setPrefHeight(60);
        Label scoreLabel = new Label(label);
        scoreLabel.setFont(Font.font(16));
        scoreLabel.setTranslateX(-15);
        Label scoreValue = new Label("0");
        scoreValue.setFont(Font.font(26));
        scoreValue.setTranslateX(40);
        getChildren().addAll(scoreLabel, scoreValue);
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

}

// DiceAreaPane class
class DiceAreaPane extends StackPane {
    private final Random random = new Random();
    private final ImageView[] diceViews = new ImageView[6];
    private final Timeline timeline = new Timeline();
    private final int[] diceValues = new int[6];
    private boolean rolling = false;
    private DicePane playerDicePane;
    private DicePane computerDicePane;

    public DiceAreaPane(DicePane playerDicePane, DicePane computerDicePane) {
        this.playerDicePane = playerDicePane;
        this.computerDicePane = computerDicePane;
        setPrefHeight(340);

        GridPane diceGrid = new GridPane();
        diceGrid.setHgap(40);
        diceGrid.setVgap(40);
        diceGrid.setPadding(new Insets(20, 20, 20, 20));

        for (int i = 0; i < 6; i++) {
            diceValues[i] = i + 1;
            Image diceImage = new Image(getClass().getResourceAsStream("/images/BlackDie" + (i + 1) + ".png"));
            diceViews[i] = new ImageView(diceImage);
            int finalI = i;
            diceViews[i].setOnMouseClicked(event -> {
                if (!rolling) {
                    // Remove the dice from the DiceAreaPane
                    diceGrid.getChildren().remove(diceViews[finalI]);
                    // Add the dice to the player's DicePane
                    playerDicePane.addDice(diceViews[finalI]);
                }
            });
            diceViews[i].setFitWidth(50);
            diceViews[i].setFitHeight(50);
            diceGrid.add(diceViews[i], i % 3, i / 3);
        }

        diceGrid.setTranslateY(60);
        diceGrid.setTranslateX(40);

        Label messageLabel = new Label("Player's Turn");
        messageLabel.setTranslateY(-135);

        Image rules = new Image(getClass().getResourceAsStream("/images/Rules.png"));
        ImageView imageView = new ImageView(rules);
        imageView.setTranslateX(170);

        Button rollButton = new Button("Roll");
        rollButton.setTranslateY(100);
        rollButton.setTranslateX(-110);
        rollButton.setFont(Font.font(12));
        rollButton.setMaxSize(82, 30);
        rollButton.setOnAction(event -> rollDice());

        Button bankScoreButton = new Button("Bank Score");
        bankScoreButton.setTranslateY(100);
        bankScoreButton.setTranslateX(20);
        bankScoreButton.setFont(Font.font(12));
        bankScoreButton.setMaxSize(82, 30);

        Button hint = new Button("?");
        hint.setShape(new Circle(10));
        hint.setMinSize(20, 20);
        hint.setMaxSize(20, 20);
        Tooltip hintTT = new Tooltip("Hint");
        hint.setFont(Font.font(10));
        hint.setTooltip(hintTT);
        hint.setTranslateX(-110);
        hint.setTranslateY(140);

        getChildren().addAll(messageLabel, diceGrid, rollButton, bankScoreButton, imageView, hint);

        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> rolling = false);

    }

    private void rollDice() {
        if (!rolling) {
            rolling = true;
            timeline.getKeyFrames().clear();
            for (int i = 0; i < 6; i++) {
                int finalI = i;
                // Add multiple keyframes to create a "shuffling" effect
                for (int j = 0; j < 10; j++) {
                    timeline.getKeyFrames().add(
                            new KeyFrame(Duration.millis(j * 100.0 + finalI * 100.0), event -> {
                                int newValue = random.nextInt(6) + 1;
                                Image newImage = new Image(getClass().getResourceAsStream("/images/BlackDie" + newValue + ".png"));
                                diceViews[finalI].setImage(newImage);
                            })
                    );
                }
                // Add a final keyframe to set the final dice value
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(1000.0 + finalI * 100.0), event -> {
                            diceValues[finalI] = random.nextInt(6) + 1;
                            Image newImage = new Image(getClass().getResourceAsStream("/images/BlackDie" + diceValues[finalI] + ".png"));
                            diceViews[finalI].setImage(newImage);
                        })
                );
            }
            timeline.play();
        }
    }

}
