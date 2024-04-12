package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root pane
        VBox root = new VBox();

        HeaderPane headerPane = new HeaderPane();
        ScorePane computerScorePane = new ScorePane("Computer\nScore");
        DicePane computerBankedDicePane = new DicePane("Computer\nBanked\nDice");
        DiceAreaPane diceAreaPane = new DiceAreaPane();
        DicePane playerBankedDicePane = new DicePane("Player\nBanked\nDice");
        ScorePane playerScorePane = new ScorePane("Player\nScore");
                
        root.getChildren().addAll(headerPane, computerScorePane, computerBankedDicePane, diceAreaPane, playerBankedDicePane, playerScorePane );

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
    public DicePane(String label) {
        setPrefHeight(60);
        Label diceLabel = new Label(label);
        diceLabel.setTranslateX(-200);
        
        HBox diceBox = new HBox(10); 
        diceBox.setAlignment(Pos.CENTER);
        diceBox.setTranslateX(20);
        
        for (int i = 1; i <= 6; i++) {
            Image dice = new Image(getClass().getResourceAsStream("/images/BlackDie" + i + ".png"));
            ImageView diceView = new ImageView(dice);
            diceView.setFitWidth(50); // Set width
            diceView.setFitHeight(50); // Set height
            diceBox.getChildren().add(diceView);
        }
        
        getChildren().addAll(diceLabel, diceBox);
    }
}

// DiceAreaPane class
class DiceAreaPane extends StackPane {
    public DiceAreaPane() {
        setPrefHeight(340);
        
        GridPane diceGrid = new GridPane();
        diceGrid.setHgap(40); 
        diceGrid.setVgap(40); 
        diceGrid.setPadding(new Insets(20, 20, 20, 20)); 

        
        for (int i = 1; i <= 6; i++) {
            Image dice = new Image(getClass().getResourceAsStream("/images/BlackDie" + i + ".png"));
            ImageView diceView = new ImageView(dice);
            diceView.setFitWidth(50); // Set width
            diceView.setFitHeight(50); // Set height

            int row = (i <= 3) ? 0 : 1;
            int col = (i <= 3) ? i - 1 : i - 4;
            diceGrid.add(diceView, col, row);
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
        
        Button bankScoreButton = new Button("Bank Score");
        bankScoreButton.setTranslateY(100);
        bankScoreButton.setTranslateX(20);
        bankScoreButton.setFont(Font.font(12));
        bankScoreButton.setMaxSize(82, 30);
        
        getChildren().addAll(messageLabel, diceGrid, rollButton, bankScoreButton, imageView);
    }
}
