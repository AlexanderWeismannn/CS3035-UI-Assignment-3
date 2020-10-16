package Part2;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class HappyFaceDisplay extends Pane {
    private Canvas canvas;
    private SimpleObjectProperty<Color> colorProperty;
    private GraphicsContext gc;

    public HappyFaceDisplay() {
        super();

        //initializing the canvas
        canvas = new Canvas(this.getWidth(),this.getHeight());
        canvas.prefHeight(600);
        canvas.prefWidth(600);
        this.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        colorProperty = new SimpleObjectProperty<Color>();
        colorProperty.setValue(Color.WHITE);
    }

    //checks when the window size is changed
    @Override
    public void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());
        //is called everytime there is a change in size
        drawHappyFace(); // a method that you write
    }

    //wipes the canvas clean and updates it based on the new size
    public void drawHappyFace(){
        double w = canvas.getWidth();
        double h = canvas.getHeight();


        //clears the canvas every time an update is made
        gc.clearRect(0,0,w,h);

        gc.setFill(colorProperty.get());
        gc.fillRect(0,0, w,h);

        gc.setFill(Color.BLACK);
        //set the line do change thickness based on the width of the canvas
        gc.setLineWidth(w*0.009);

        //re-adjusting size of the face based on the width / height of the canvas
        gc.strokeOval(w*0.05, h*0.05, w*0.9, h*0.9);
        gc.fillRect(w*0.30,h*0.30,w*0.04,h*0.08);
        gc.fillRect(w*0.65,h*0.30,w*0.04,h*0.08);
        //changes based on max, min, pref size

        if(this.getWidth() >= this.getMaxWidth()){
            //smile
            gc.strokeArc(w*0.35, h*0.65, w*0.3, h*0.1, 180, 180, ArcType.OPEN);

        }else if(this.getWidth() >= this.getPrefWidth()){
            // line
            gc.strokeLine(w*(0.3),h*(0.5),w*(0.7),h*(0.5));
        }else{
            //frown
            gc.strokeArc(w*0.35, h*0.65, w*0.3, h*0.1, 0, 180, ArcType.OPEN);

        }




    }

    //getter for the color Object
    public SimpleObjectProperty<Color> getColorProperty(){
        return colorProperty;
    }

    //setter for the color object
    public void setColorProperty(Color newColor){
        colorProperty.set(newColor);
    }




}