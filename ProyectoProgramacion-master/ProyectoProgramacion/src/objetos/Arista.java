/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-09-07 
 *
 */
public class Arista extends Parent
{
    Nodo entrada;
    Nodo salida;
    Line arista;
    public Arista(Nodo entrada, Nodo salida)
    {
        this.entrada = entrada;
        this.salida = salida;
        double x1 = entrada.getPosicion(true);
        double y1 = entrada.getPosicion(false);
        double x2 = salida.getPosicion(true);
        double y2 = salida.getPosicion(false);
        if(y1 < y2)
        {
            y1+=50;
            y2-=50;
        }else if(y2 < y1){
            y1-=50;
            y2+=50;
        }
            
        arista = new Line(x1,y1,x2,y2);
        arista.setFill(Color.BLACK);
        arista.setStrokeWidth(2);
        this.getChildren().add(arista);
    }
    
    public void moverArista(int x1, int y1, int x2, int y2)
    {
        this.arista.setStartX(x1);
        this.arista.setStartY(y1);
        this.arista.setEndX(x2);
        this.arista.setEndY(y2);
    }
}
