/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoprogramacion;

import com.sun.javafx.geom.Ellipse2D;
import com.sun.prism.Graphics;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-08-18 
 *
 */
public class CanvasGrafo extends Canvas
{
    Cursor curCursor;
    
    public CanvasGrafo()
    {
      this.setWidth(400);
      this.setHeight(400);
      
      Rectangle rect = new Rectangle(120, 75);
      rect.setFill(Color.RED);
      this.getGraphicsContext2D().setFill(Color.GREEN);
      this.getGraphicsContext2D().fillRect(50, 50, 50, 50);
       
       //circle_Green.setOnMousePressed(circleOnMousePressedEventHandler);
       //circle_Green.setOnMouseDragged(circleOnMouseDraggedEventHandler);
    }

}
