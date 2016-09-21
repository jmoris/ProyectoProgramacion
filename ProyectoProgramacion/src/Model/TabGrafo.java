/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import javafx.scene.control.Tab;

/**
 *
 * @author Jesus Moris Raimundo Vásquez Rodrigo Letelier
 * @version 2016-08-16 
 *
 */
public class TabGrafo extends Tab
{
    private int c;
    private Whiteboard pizarra;
   
    /**
     * 
     * @param grafo
     * @param w
     * @param h 
     * Constructor para pestañas nuevas
     */
    public TabGrafo(int counter,double w, double h)
    {
        super();
       
        pizarra = new Whiteboard();
        this.setContent(pizarra);
        this.setText("Sin titulo-" + counter);
        this.setId(String.valueOf(counter));
    }
    public TabGrafo(double w, double h, String name)
    {
        super();
       
        pizarra = new Whiteboard();
        this.setContent(pizarra);
        this.setText(name);
    }   
    public void saveGraph(String path)
    {
        this.pizarra.saveGraph(path);
    }
    
    public void openGraph(String path)
    {
        this.pizarra.openGraph(path);
    }
    
    public void agregarNodo(Boolean tipo, double x, double y, double ancho)
    {
        Node asd = new Node(c, "", new Point(x, y), ancho, 1f, (tipo) ? 1 : 2 );
        c++;
        pizarra.addNode(asd);
    }
    
    public Node getNodo(int i)
    {
        return this.pizarra.getNode(i);
    }
    
    public Graph getGrafo()
    {
        return this.pizarra.getGraph();
    }
    
}
