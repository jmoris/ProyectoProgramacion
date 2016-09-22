/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import static java.lang.Math.abs;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-09 
 *
 */
@Root(name="Arista")
public class Edge extends Parent
{
    @Element(name="Entrada")
    Node n1;
    @Element(name="Salida")
    Node n2;
    Line l1;
    Line l2;
    Line l3;
    public Edge(@Element(name="Entrada")Node n1, @Element(name="Salida")Node n2)
    {
        this.n1 = n1;
        this.n2 = n2;
        this.l1 = new Line();
        this.l1.setStroke(Color.RED);
        this.l1.setStrokeWidth(3);
        this.l2 = new Line();
        this.l2.setStroke(Color.RED);
        this.l2.setStrokeWidth(3);
        this.l3 = new Line();
        this.l3.setStroke(Color.RED);
        this.l3.setStrokeWidth(3);
        calculateLinePos();
        this.getChildren().addAll(this.l1,this.l2,this.l3);
    }
    
    private void calculateLinePos(){
        double c1x = this.n1.getPos().getPointX()+this.n1.getNodeWidth(); // Centro del nodo 1 X
        double c1y = this.n1.getPos().getPointY()+this.n1.getNodeWidth(); // Centro del nodo 1 Y
        double c2x = this.n2.getPos().getPointX()+this.n2.getNodeWidth(); // Centro del nodo 2 X
        double c2y = this.n2.getPos().getPointY()+this.n2.getNodeWidth(); // Centro del nodo 2 Y
        
        double d1x = c1x - c2x; // Distancia entre el nodo 1 y el 2 punto x
        double d1y = c1y - c2y; // Distancia entre el nodo 1 y el 2 punto y
        // Se modifica la posicion de las 3 lineas(solo sirve entre circulos)
        
            this.l1.setStartX(c1x);
            this.l1.setStartY((d1y > 0) ? c1y-this.n1.getNodeWidth() : c1y+this.n1.getNodeWidth());
            this.l1.setEndX(c1x);
            this.l1.setEndY(c1y-(d1y/2));

            this.l2.setStartX(c1x);
            this.l2.setStartY(c1y-(d1y/2));
            this.l2.setEndX(c2x);
            this.l2.setEndY(c1y-(d1y/2));

            this.l3.setStartX(c2x);
            this.l3.setStartY((d1y > 0) ? c2y+this.n2.getNodeWidth() : c2y-this.n2.getNodeWidth());
            this.l3.setEndX(c2x);
            this.l3.setEndY(c2y+(d1y/2));        
    }

    public void refresh(){
        calculateLinePos();
    }
    
}
