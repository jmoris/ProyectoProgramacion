/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoprogramacion;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-08-16 
 *
 */
public class TabGrafo extends Tab
{
    private Grafo grafo;
    private Pizarra pizarra;
    
    public TabGrafo(Grafo grafo, double w, double h)
    {
        super();
        this.grafo = grafo;
        pizarra = new Pizarra();
        this.setContent(pizarra);
        this.setText("Sin titulo-" + grafo.id);
        this.setId(String.valueOf(grafo.id));
    }
    
    public void agregarNodo(Boolean tipo, double x, double y, double ancho)
    {
        pizarra.añadirNodo(tipo, x, y, ancho);
    }
    
    public int nodosSize()
    {
        return this.pizarra.nodosSize();
    }
    
    public Nodo getNodo(int i)
    {
        return this.pizarra.getNodo(i);
    }
    
    public Grafo getGrafo()
    {
        return this.grafo;
    }
    
}
