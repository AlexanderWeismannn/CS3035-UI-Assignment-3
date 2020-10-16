package Part2;

import Utility.ColorUtility;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {
    public void start(Stage primaryStage) throws IOException {
        int i = 0;
        BorderPane root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        MyFlowPane flowPane = (MyFlowPane) root.getCenter();
        ColorUtility utility = new ColorUtility();
        SimpleObjectProperty<Color> colorObject = new SimpleObjectProperty<Color>(Color.WHITE);



        //"dig down" into the pane -> vbox and declare the individual buttons
        VBox rightBox = (VBox) root.getRight();
        ListView list = (ListView) rightBox.lookup("#listView");
        Button clearAllButton = (Button)rightBox.lookup("#clearAll");
        Button addFaceButton = (Button)rightBox.lookup("#addFace");

        // adding the hashmap of colors to the listView
        list.getItems().addAll(utility.getColorNameList());


        //adding an event listener to change the background color of the canvas
        list.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    colorObject.set(ColorUtility.getColorsMap().get(newValue.toString()));
                    System.out.println(newValue.toString());
                }
        );





        addFaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //setting up the random width/heights values for the new happyface Object
                double minWH = ThreadLocalRandom.current().nextInt(10,81);
                double prefWH = ThreadLocalRandom.current().nextInt(100,201);
                double maxWH = ThreadLocalRandom.current().nextInt(250,501);

                //initialize the new object
                HappyFaceDisplay newHappyFace = new HappyFaceDisplay();
                //set its new sizes
                newHappyFace.setMinSize(minWH,minWH);
                newHappyFace.setPrefSize(prefWH,prefWH);
                newHappyFace.setMaxSize(maxWH,maxWH);
                newHappyFace.setColorProperty(colorObject.get());
                //add it to the flowpane
                //add to the correct positioning
                flowPane.getChildren().add(newHappyFace);
            }
        });

        clearAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                flowPane.getChildren().remove(0,flowPane.getChildren().size());
            }
        });











        Label label = (Label) root.getBottom();
        label.textProperty().bind(Bindings.concat(flowPane.widthProperty(),",",flowPane.heightProperty()));


        //create the scene, set the title, set the stage, show it.
        Scene scene = new Scene(root);
        primaryStage.setTitle("Assignment 3, Part 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}