package com.example.tic_tac_toc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private Label playerXScoreLabel, playerYScoreLabel;
    private Button buttons[][] = new Button[3][3];
    private BorderPane createContent(){

        // title

        BorderPane root = new BorderPane();
        Label titleLabel = new Label("Tic Tac Toe" );
        root.setTop(titleLabel);
        titleLabel.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");

        //Board
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("-");
                button.setPrefSize(100,100);
                buttons[i][j] = button;
                gridPane.add(button,i,j);
            }
        }
        root.setCenter(gridPane);
        //Player title

        HBox scoreBoard = new HBox(20);
        playerXScoreLabel = new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        playerYScoreLabel = new Label("Player Y : 0");
        playerYScoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerYScoreLabel);
        root.setBottom(scoreBoard);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}