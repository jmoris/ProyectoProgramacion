/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoprogramacion;

import objetos.Grafo;
import objetos.Nodo;
import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import objetos.Arista;

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
    int totalNodos;
    ArrayList<Nodo> nodos;
    Nodo nuevo;
    Boolean creacion;
    
    public Pizarra()
    {
        this.setStyle("-fx-background-color:#FFFFFF");
        this.creacion = false;
        grafo = new Grafo();
        this.nodos = new ArrayList<Nodo>();
        this.totalNodos = 0;
        this.setOnScroll(this::redimensionar);
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
    
    
    public void añadirNodo(Boolean tipo, double x, double y, double ancho)
    {
        String etiqueta = "";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Titulo del nodo");
        dialog.setHeaderText("Ingrese la etiqueta del nodo seleccionado.");
        dialog.setContentText("Etiqueta:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            etiqueta = result.get();
        }
        if(tipo)
        {
            nuevo = new Nodo(totalNodos, etiqueta, x, y, ancho, 1);
        }else{
            nuevo = new Nodo(totalNodos, etiqueta, x, y, ancho, 2);
        }
        this.nodos.add(nuevo);
        this.getChildren().add(nuevo);
        this.creacion = true;
    }
    
    public void añadirArista(Arista arista)
    {
        System.out.println("Sigue intento de creacion");
        this.getChildren().add(arista);
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
