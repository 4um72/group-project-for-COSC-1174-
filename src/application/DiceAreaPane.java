package application;

import java.util.Arrays;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class DiceAreaPane extends StackPane {
    private final Random random = new Random();
    private final ImageView[] diceViews = new ImageView[6];
    private final Timeline timeline = new Timeline();
    private final int[] diceValues = new int[6];
    private boolean rolling = false;
    private DicePane playerDicePane;
    private DicePane computerDicePane;
    private GridPane diceGrid;

    public DiceAreaPane(DicePane playerDicePane, DicePane computerDicePane) {
        this.playerDicePane = playerDicePane;
        this.computerDicePane = computerDicePane;
        setPrefHeight(340);

        diceGrid = new GridPane();
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
                    // Move dice to playerBankedDicePane
                    updateScoreAndMoveDice(diceViews[finalI]);
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

        getChildren().addAll(messageLabel, diceGrid, rollButton, imageView, bankScoreButton, hint);

        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> rolling = false);
    }

    private void updateScoreAndMoveDice(ImageView dice) {
        int finalI = Arrays.asList(diceViews).indexOf(dice);
        if (finalI != -1) {
            // Remove the dice from the DiceAreaPane
            diceGrid.getChildren().remove(dice);
            // Add the dice to the player's banked dice pane
            playerDicePane.addDice(dice);
            
            dice.setOnMouseClicked(event -> {
                if (!rolling) {
                    // Move dice back to diceAreaPane
                    updateScoreAndMoveDice(dice);
                }
            });
        }
    }

    private void rollDice() {
        if (!rolling) {
            rolling = true;
            timeline.getKeyFrames().clear();
            for (int i = 0; i < 6; i++) {
                int finalI = i;
                // Only roll the dice if it is still in the diceGrid
                if (diceGrid.getChildren().contains(diceViews[finalI])) {
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
            }
            timeline.play();
            
            // Remove click events from all dice in the player's banked dice pane
            playerDicePane.removeAllClickEvents();
        }
    }
}
