/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoprogramacion;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-09-02 
 *
 */
public class Nodo extends Parent
{
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int id;
    String etiqueta;
    double x, y;
    Shape nodo;
    
    public Nodo(int id, String etiqueta, double x, double y, double ancho, int tipo)
    {
        /*
            TIPO 1: CIRCULO
            TIPO 2: CUADRADO
        */
        this.id = id;
        this.etiqueta = etiqueta;
        this.x = x;
        this.y = y;
        switch(tipo)
        {
            case 1:
                nodo = new Circle(x, y, ancho, Color.WHITE);
                nodo.setStroke(Color.BLACK);
                break;
            case 2:
                nodo = new Rectangle(x, y, ancho, ancho/2);
                nodo.setFill(Color.WHITE);
                nodo.setStroke(Color.BLACK);
                break;
        }
        nodo.setOnMousePressed(this::presionar);
        nodo.setOnMouseReleased(this::soltar);
        nodo.setOnMouseDragged(this::mover);
        getChildren().add(nodo);
    }
    
    
    public void presionar(MouseEvent t)
    {
        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();
        orgTranslateX = ((Shape)(t.getSource())).getTranslateX();
        orgTranslateY = ((Shape)(t.getSource())).getTranslateY();
        
        ((Shape)this.nodo).setStroke(Color.RED);
    }
    
    public void soltar(MouseEvent t)
    {
        ((Shape)this.nodo).setStroke(Color.BLACK);
    }
    
    public void mover(MouseEvent t)
    {
        double offsetX = t.getSceneX() - orgSceneX;
        double offsetY = t.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;
             
        ((Shape)(t.getSource())).setTranslateX(newTranslateX);
        ((Shape)(t.getSource())).setTranslateY(newTranslateY);
    }
    
    public void setPosicion(int x, int y)
    {
        if(this.x != x)
            this.x = x;
        if(this.y != y)
            this.y = y;
    }
    
    public double getPosicion(Boolean eje)
    {
        if(eje)
            return this.x;
        else
            return this.y;
    }

}
