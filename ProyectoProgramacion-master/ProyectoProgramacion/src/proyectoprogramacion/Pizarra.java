/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoprogramacion;

import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-09-02 
 *
 */
public class Pizarra extends Pane
{
    final double SCALE_DELTA = 1.1;
    Grafo grafo;
    SelectionModel select;
    int totalNodos;
    ArrayList<Nodo> nodos;
    Nodo nuevo;
    Boolean creacion;
    
    public Pizarra()
    {
        this.creacion = false;
        this.select = new SelectionModel();
        grafo = new Grafo();
        this.nodos = new ArrayList<Nodo>();
        this.totalNodos = 0;
        this.setOnScroll(this::redimensionar);
        this.setOnMouseClicked(this::click);
        this.setOnMouseMoved(this::mueve);
        this.setOnMouseReleased(this::quita);
    }
    
    public void redimensionar(ScrollEvent event)
    {
        event.consume();

        if (event.getDeltaY() == 0) {
          return;
        }

        double scaleFactor =
          (event.getDeltaY() > 0)
            ? SCALE_DELTA
            : 1/SCALE_DELTA;

        this.setScaleX(this.getScaleX() * scaleFactor);
        this.setScaleY(this.getScaleY() * scaleFactor);
    }
    
    private void click(MouseEvent e)
    {
        if(this.creacion)
        {
            nuevo = new Nodo(totalNodos, "Nodo", e.getSceneX(), e.getSceneY(), 75, 2);
            getChildren().add(nuevo);
            nuevo.setVisible(true);
        }
        
    }
    
    private void select(MouseEvent e)
    {
        
    }
    private void mueve(MouseEvent e)
    {
        if(this.creacion)
        {
            if(nuevo.isVisible() && nuevo.nodo instanceof Rectangle)
            {
                ((Rectangle)nuevo.nodo).setWidth(e.getX() - nuevo.nodo.getTranslateX());
                ((Rectangle)nuevo.nodo).setHeight(e.getY() - nuevo.nodo.getTranslateX());
            }
        }
    }
    
    private void quita(MouseEvent e)
    {
        if(this.creacion)
        {
            //nuevo.setVisible(false);
            this.creacion = false;
        }
    }
    
    public void añadirNodo(Boolean tipo, double x, double y, double ancho)
    {
        if(tipo)
        {
            nuevo = new Nodo(totalNodos, "Nodo", x, y, ancho, 1);
        }else{
            nuevo = new Nodo(totalNodos, "Nodo", x, y, ancho, 2);
        }
        this.nodos.add(nuevo);
        this.getChildren().add(nuevo);
        this.creacion = true;
    }
    
    public Nodo getNodo(int i)
    {
        return this.nodos.get(i);
    }
    
    public int nodosSize()
    {
        return this.nodos.size();
    }
    
    public Grafo getGrafo()
    {
        return this.grafo;
    }
    
}
