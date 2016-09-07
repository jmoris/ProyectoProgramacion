/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 *
 * @author Jesus Moris <contacto@soluciontotal.cl>
 * @version 2016-09-02 
 *
 */
public class Nodo extends Parent
{
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int id;
    String etiqueta;
    double x, y;
    StackPane layout;
    Shape nodo;
    Text texto;
    ContextMenu contextMenu;
    
    public Nodo(int id, String etiqueta, double x, double y, double ancho, int tipo)
    {
        /*
            TIPO 1: CIRCULO
            TIPO 2: CUADRADO
        */
        layout = new StackPane();
        this.id = id;
        this.etiqueta = etiqueta;
        texto = new Text(this.etiqueta);
        texto.setFill(Color.BLACK);
        this.x = x;
        this.y = y;
        switch(tipo)
        {
            case 1:
                nodo = new Circle(x, y, ancho, Color.WHITE);
                nodo.setStroke(Color.BLACK);
                break;
            case 2:
                nodo = new Rectangle(x, y, ancho, ancho/2);
                nodo.setFill(Color.WHITE);
                nodo.setStroke(Color.BLACK);
                break;
        }
        layout.setOnMousePressed(this::presionar);
        layout.setOnMouseReleased(this::soltar);
        layout.setOnMouseDragged(this::mover);
        layout.getChildren().addAll(nodo, texto);
        this.getChildren().add(layout);
        contextMenu = new ContextMenu();
        MenuItem eliminar = new MenuItem("Eliminar");
        eliminar.setId("eliminar");
        MenuItem cambiarNombre = new MenuItem("Cambiar nombre");
        cambiarNombre.setId("cnombre");
        contextMenu.getItems().addAll(eliminar, cambiarNombre);
        contextMenu.setOnAction(this::popupMenu);
    }
    
    private void popupMenu(ActionEvent e)
    {
        MenuItem it = (MenuItem)e.getTarget();
        if(it.getId()=="cnombre")
        {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Titulo del nodo");
            dialog.setHeaderText("Ingrese la etiqueta del nodo seleccionado.");
            dialog.setContentText("Etiqueta:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                this.etiqueta = result.get();
                this.texto.setText(this.etiqueta);
            }         
        }else if(it.getId()=="eliminar"){
            this.getChildren().remove(this.nodo);
            this.getChildren().remove(this.texto);
            this.setVisible(false);
            this.setDisable(true);
        }
    }
    
    public void presionar(MouseEvent t)
    {
        if(t.isSecondaryButtonDown())
        {
            contextMenu.show(this, t.getScreenX(), t.getScreenY());
        }
        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();
        orgTranslateX = layout.getTranslateX();
        orgTranslateY = layout.getTranslateY();
        
        ((Shape)this.nodo).setStroke(Color.RED);
    }
    
    public void soltar(MouseEvent t)
    {
        ((Shape)this.nodo).setStroke(Color.BLACK);
    }
    
    public void mover(MouseEvent t)
    {
        double offsetX = t.getSceneX() - orgSceneX;
        double offsetY = t.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;
        
        layout.setTranslateX(newTranslateX);
        layout.setTranslateY(newTranslateY);
        this.x = layout.getTranslateX()+(layout.getWidth()/2);
        this.y = layout.getTranslateY()+(layout.getHeight()/2);
    }
    
    public void setPosicion(int x, int y)
    {
        if(this.x != x)
            this.x = x;
        if(this.y != y)
            this.y = y;
    }
    
    public double getPosicion(Boolean eje)
    {
        if(eje)
            return this.x;
        else
            return this.y;
    }

}
