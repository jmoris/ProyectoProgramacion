/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoprogramacion;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-09-06 
 *
 */
public class Punto 
{
    private double x, y;
    
    public Punto(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Punto()
    {
        this.x = 0;
        this.y = 0;
    }
    
    public Boolean isEmpty()
    {
        return (this.x== 0 && this.y== 0) ? true : false;
    }
    
    public void setPosicion(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double[] getPosicion()
    {
        double[] arr = {this.x, this.y};
        return arr;
    }
    
}
