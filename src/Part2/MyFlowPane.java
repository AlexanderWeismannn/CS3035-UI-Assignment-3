package Part2;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

public class MyFlowPane extends Pane {

    public MyFlowPane() {
        super();

        //add in clipping to the pane
        Rectangle clipRectangle = new Rectangle();
        this.setClip(clipRectangle);
        this.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            clipRectangle.setWidth(newValue.getWidth());
            clipRectangle.setHeight(newValue.getHeight());
        });

    }

    //
    @Override
    public void layoutChildren() {
        //*****calculate these*****
        double prefWidth = 0;
        double prefHeight = 0;
        double maxWidth = 0;
        double maxHeight = 0;
        double minWidth = 0;
        double minHeight = 0;



        // first determine the size parameters for all children
        // by looping through and adding them up
        for (int i = 0; i < getChildren().size(); i++) {
            Region r = (Region) getChildren().get(i);
            //defining all for posterity
            //get pref Values
            prefHeight += r.getPrefHeight();
            prefWidth += r.getPrefWidth();
            //get min Values
            minHeight += r.getMinHeight();
            minWidth += r.getMinWidth();
            //get Max Values
            maxHeight += r.getMaxHeight();
            maxWidth += r.getMaxWidth();
        }

        //next size the children as appropriate
        //use max dimensions if there is enough space horizontal space
        if (maxWidth <= this.getWidth()) {
            //Add something here
            for(int i = 0; i < getChildren().size(); i++){
                Region r = (Region) getChildren().get(i);
                //resizing the demensions to be its max width and height
                r.resize(r.getMaxWidth(),r.getMaxHeight());
            }
            //else use preferred dimensions if there is enough horizontal space
        }else if(prefWidth <= this.getWidth()){
            //Add something here
            for(int i = 0; i < getChildren().size(); i++){
                Region r = (Region) getChildren().get(i);
                //resizing the dimensions to be its pref width and height
                r.resize(r.getPrefWidth(),r.getPrefHeight());
            }
            // else us the min dimensions. If it goes smaller than this it will begin to clip
        }else{
            //Add something here
            for(int i = 0; i < getChildren().size(); i++){
                Region r = (Region) getChildren().get(i);
                r.resize(r.getMinWidth(),r.getMinHeight());
            }
        }



        if(getChildren().size() > 1){
            Region first = (Region) getChildren().get(0);
            double prevW = first.getWidth();
            double prevH = first.getHeight();

            for(int i = 1; i < getChildren().size(); i++){
                Region r = (Region) getChildren().get(i);
                r.relocate(prevW,prevH);
                prevW += r.getWidth();
                prevH += r.getHeight();

            }
        }
    }






}




