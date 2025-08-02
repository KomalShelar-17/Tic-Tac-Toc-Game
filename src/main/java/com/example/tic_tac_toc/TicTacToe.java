package com.example.tic_tac_toc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private Label playerXScoreLabel, playerOScoreLabel;
    private Button[][] buttons = new Button[3][3];
    private boolean playerXTurn=true, playerOTurn;
    private boolean gameOver = false;
    private int playerXScore=0, playerOScore=0;
    private BorderPane createContent(){

        // title

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        Label titleLabel = new Label("Tic Tac Toe" );
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        titleLabel.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold;");

        //Board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size : 25pt; -fx-font-weight : bold;");
                button.setOnAction(event->buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button,i,j);
            }
        }

        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);
        //Player title

        HBox scoreBoard = new HBox(20);
        playerXScoreLabel = new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        playerOScoreLabel = new Label("Player Y : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerOScoreLabel);
        root.setBottom(scoreBoard);
        return root;
    }
    private void buttonClicked(Button button){

        if (gameOver || !button.getText().equals("")) {
            return;
        }
        if (playerXTurn) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        playerXTurn = !playerXTurn;
        checkWinner();

    }

    private void checkWinner(){
        //Row
        for(int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                    !buttons[row][0].getText().isEmpty()) {
                String winner = buttons[row][0].getText();
                gameOver = true;
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // column
        for(int col = 0; col < 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText()) &&
                    !buttons[0][col].getText().isEmpty()) {
                String winner = buttons[0][col].getText();
                gameOver = true;
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // Diagonal check
        if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().isEmpty()) {
            String winner = buttons[0][0].getText();
            gameOver = true;
            showWinnerDialog(winner);
            resetBoard();
            updateScore(winner);
            return;
        }

        if(buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().isEmpty()) {
            String winner = buttons[0][2].getText();
            gameOver = true;
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        boolean tie = true;
        for(Button row[] : buttons){
            for(Button button : row){
                if(button.getText().isEmpty()){
                    tie=false;
                    break;
                }
            }
        }
        if(tie){
            showTieDialog();
            resetBoard();
        }

    }

    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulation " + winner + "! You Won the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over ! It's a Tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXScore++;
            playerXScoreLabel.setText("Player X : " + playerXScore);
        }
        else {
            playerOScore++;
            playerOScoreLabel.setText("Player O :" + playerOScore);
        }
    }

    private void resetBoard(){
        for(Button row[] : buttons){
            for(Button button : row){
                button.setText("");
            }
        }
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