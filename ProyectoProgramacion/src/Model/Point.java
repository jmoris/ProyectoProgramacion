package Model;

import org.simpleframework.xml.Element;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-09 
 *
 */
public class Point 
{
    @Element(name="X")
    private double pointX;
    @Element(name="Y")
    private double pointY;
    /**
     * Constructor principal, se crean los puntos en la coordenada (0, 0).
     */
    public Point(){
        this.pointX = 0;
        this.pointY = 0;
    }
    /**
     * Constructor secundario, se crean los puntos en la coordenada indicada.
     * @param pointX Posicion X
     * @param pointY Posicion Y
     */
    public Point(double pointX, double pointY){
        this.pointX = pointX;
        this.pointY = pointY;
    }
    /**
     * Metodo para fijar el punto X.
     * @param pointX Posicion X
     */
    public void setPointX(double pointX){
        this.pointX = pointX;
    }
    /**
     * Metodo para fijar el punto Y.
     * @param pointY Posicion Y
     */
    public void setPointY(double pointY){
        this.pointY = pointY;
    }
    /**
     * Metodo para obtener el punto X.
     * @return Posicion X
     */
    public double getPointX(){
        return this.pointX;
    }
    /**
     * Metodo para obtener el punto Y.
     * @return Posicion Y
     */
    public double getPointY(){
        return this.pointY;
    }
}
