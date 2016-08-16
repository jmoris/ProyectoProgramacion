/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoprogramacion;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-08-16 
 *
 */
public class TabGrafo extends Tab
{
    private Grafo grafo;
    private Canvas canvas;
    
    public TabGrafo(Grafo grafo)
    {
        super();
        this.grafo = grafo;
        canvas = new Canvas();
        this.setContent(canvas);
        this.setText("Sin titulo-" + grafo.id);
        this.setId(String.valueOf(grafo.id));
    }
    
    public Grafo getGrafo()
    {
        return this.grafo;
    }
    
}
