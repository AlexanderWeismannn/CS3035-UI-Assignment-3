package Part1;


import Utility.ColorUtility;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage primaryStage) throws IOException {

        BorderPane root = FXMLLoader.load(getClass().getResource("Interface.fxml"));

        //get components
        HappyFaceDisplay happyFace = (HappyFaceDisplay) root.getCenter();
        ListView list = (ListView) root.getRight();
        ColorUtility utility = new ColorUtility();

        // adding the hashmap of colors to the listView
        list.getItems().addAll(utility.getColorNameList());

        //adding an event listener to change the background color of the canvas
        list.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    //update the face's color
                    //calls the hashmap with the newValue.toString() key
                    happyFace.setColorProperty(utility.getColorsMap().get(newValue.toString()));
                    //call the drawHappyFace() to update the background color after its been clicked not just moved
                    happyFace.drawHappyFace();
                    System.out.println(newValue.toString());
                }
        );



        //binds the label text to the width and height of the happyFace Object
        Label label = (Label) root.getBottom();
        label.textProperty().bind(Bindings.concat(happyFace.widthProperty(),",",happyFace.heightProperty()));

        //create the scene, set the title, set the stage, show it.
        Scene scene = new Scene(root);
        primaryStage.setTitle("Assignment 3, Part 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}