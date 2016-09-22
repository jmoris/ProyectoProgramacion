/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-09 
 *
 */
@Root(name="Nodo")
public class Node extends Parent
{
    // Atributos para posicionamiento grafico
    @Element(name="Id")
    private int nodeId;
    @Element(name="Etiqueta")
    private String nodeLabel;
    @Element(name="Posicion")
    private Point pos;
    @Element(name="Ancho")
    private double nodeWidth;
    @Element(name="Grosor")
    private double nodeStroke;
    @Element(name="Tipo")
    private int nodeType;
    public NodeControl control;
    private boolean active;
    // Atributos para construccion del elemento grafico
    private StackPane outLayout;
    private Shape nodeForm;
    private Text nodeText;
    private ContextMenu contextMenu;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    
    public Node(@Element(name="Id")int nodeId, @Element(name="Etiqueta")String nodeLabel, @Element(name="Posicion")Point pos, @Element(name="Ancho")double nodeWidth, @Element(name="Grosor") double nodeStroke, @Element(name="Tipo")int nodeType)
    {
        this.nodeId = nodeId;
        this.nodeLabel = nodeLabel;
        this.pos = pos;
        this.nodeWidth = nodeWidth;
        this.nodeStroke = nodeStroke;
        this.nodeType = nodeType;
        this.active = false;
        
        this.outLayout = new StackPane();
        
        this.nodeText = new Text(this.nodeLabel);
        this.nodeText.setFill(Color.BLACK);
                
        switch(nodeType){
            case 1:
                this.nodeForm = new Circle(this.pos.getPointX(), this.pos.getPointY(), this.nodeWidth, Color.WHITE);
                this.nodeForm.setStroke(Color.BLACK);
                this.nodeForm.setStrokeWidth(this.nodeStroke);
                break;
            case 2:
                this.nodeForm = new Rectangle(this.pos.getPointX(), this.pos.getPointY(), this.nodeWidth, this.nodeWidth/2);
                this.nodeForm.setFill(Color.WHITE);
                this.nodeForm.setStroke(Color.BLACK);
                this.nodeForm.setStrokeWidth(this.nodeStroke);
                break;
        }
        this.outLayout.getChildren().addAll(this.nodeForm, this.nodeText);
        this.outLayout.relocate(this.pos.getPointX(), this.pos.getPointY());
        this.control = new NodeControl(this.outLayout);
        this.control.addPropertyChangeListener(this::onNodeMove);
        this.outLayout.getStyleClass().add("node");
        this.getChildren().add(this.outLayout);
        contextMenu = new ContextMenu();
        MenuItem delete = new MenuItem("Eliminar");
        delete.setId("eliminar");
        MenuItem changeName = new MenuItem("Cambiar nombre");
        changeName.setId("cnombre");
        MenuItem changeColor = new MenuItem("Cambiar color");
        changeColor.setId("ccolor");
        MenuItem changeStrokeSize = new MenuItem("Cambiar ancho");
        changeStrokeSize.setId("cancho");
        contextMenu.getItems().addAll(delete, changeName, changeStrokeSize);
        contextMenu.setOnAction(this::popupMenu);
        this.setOnMousePressed(this::onClickEvent);
    }

    private void onClickEvent(MouseEvent evt){
        if(evt.isSecondaryButtonDown()){
            this.outLayout.getStyleClass().clear();
            this.outLayout.getStyleClass().add("selected-node");
            this.contextMenu.show(this, evt.getScreenX(), evt.getScreenY());
        }
    }
    
    private void popupMenu(ActionEvent evt){
        MenuItem it = (MenuItem)evt.getTarget();
        if(it.getId()=="cnombre")
        {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Titulo del nodo");
            dialog.setHeaderText("Ingrese la etiqueta del nodo seleccionado.");
            dialog.setContentText("Etiqueta:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                this.nodeLabel = result.get();
                this.nodeText.setText(this.nodeLabel);
            }         
        }else if(it.getId()=="eliminar"){
            this.setVisible(false);
            this.setDisable(true);
        }else if(it.getId()=="cancho"){
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Ancho del nodo");
            dialog.setHeaderText("Ingrese el ancho para los bordes del nodo.");
            dialog.setContentText("Ancho (1.0):");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                this.nodeStroke = Double.parseDouble(result.get());
                this.nodeForm.setStrokeWidth(Double.parseDouble(result.get()));
            }               
        }
        this.outLayout.getStyleClass().clear();
        this.outLayout.getStyleClass().add("node");
    }
    
    private void onNodeMove(PropertyChangeEvent evt){
        String propertyName = evt.getPropertyName();
        if(propertyName == "activeNode"){
            this.active = (boolean)evt.getNewValue();
            changes.firePropertyChange("clickedId", null, this.getNodeId());
        }else{
            pos = (Point)evt.getNewValue();
            this.outLayout.relocate(pos.getPointX(), pos.getPointY());
            changes.firePropertyChange("nodePos", null, pos);
        }
    }

    public int getNodeId(){
        return nodeId;
    }

    public void setNodeId(int nodeId){
        this.nodeId = nodeId;
    }

    public String getNodeLabel(){
        return nodeLabel;
    }

    public void setNodeLabel(String nodeLabel){
        this.nodeLabel = nodeLabel;
        this.nodeText.setText(nodeLabel);
    }

    public Point getPos(){
        return pos;
    }

    public void setPos(Point pos){
        this.pos = pos;
        this.relocate(pos.getPointX(), pos.getPointY());
    }

    public double getNodeWidth(){
        return nodeWidth;
    }

    public void setNodeWidth(double nodeWidth){
        this.nodeWidth = nodeWidth;
        if(this.nodeForm instanceof Circle){
            ((Circle)this.nodeForm).setRadius(nodeWidth);
        }else if(this.nodeForm instanceof Rectangle){
            ((Rectangle)this.nodeForm).setWidth(nodeWidth);
            ((Rectangle)this.nodeForm).setHeight(nodeWidth/2);
        }
    }

    public int getNodeType(){
        return nodeType;
    }

    public void setNodeType(int nodeType){
        this.nodeType = nodeType;
    }
    
    public void setActive(boolean active){
        this.active = active;
    }
    
    public boolean getActive(){
        return this.active;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }   
}
