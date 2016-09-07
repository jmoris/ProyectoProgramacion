/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import objetos.Grafo;
import objetos.Nodo;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import proyectoprogramacion.Pizarra;

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
    private ScrollPane graf;
    
    public TabGrafo(Grafo grafo, double w, double h)
    {
        super();
       
        this.grafo = grafo;
        pizarra = new Pizarra();
        graf = new ScrollPane(pizarra);
        graf.setFitToHeight(true);
        graf.setFitToWidth(true);
        graf.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        graf.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        this.setContent(graf);
        this.setText("Sin titulo-" + grafo.getId());
        this.setId(String.valueOf(grafo.getId()));
    }
    
    public void agregarArista(Arista arista)
    {
        pizarra.añadirArista(arista);
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
