package com.example.ficherchess;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800); // Adjusted size for chessboard
        stage.setTitle("FischerRandomChess!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}