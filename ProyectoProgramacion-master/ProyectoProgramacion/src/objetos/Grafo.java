/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import objetos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-08-16 
 *
 */
public class Grafo 
{
    private int id;
    private String label;
    private ArrayList<Nodo> nodos;
    
    public Grafo()
    {
        this.nodos = new ArrayList<Nodo>();
    }

    public void agregarNodo(Nodo nodo)
    {
        this.nodos.add(nodo);
    }
    
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
    
    
}
