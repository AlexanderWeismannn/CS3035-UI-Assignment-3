package Part1;

import Utility.ColorUtility;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Interface.fxml"));

        Scene scene = new Scene(root);


        primaryStage.setTitle("Assignment 3, Part 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}