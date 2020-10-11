package Part2;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class HappyFaceDisplay extends Pane {
    private Canvas canvas;
    private SimpleObjectProperty<Color> colorProperty;

    public HappyFaceDisplay() {
        super();

    }
}