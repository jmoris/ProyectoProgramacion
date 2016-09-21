/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-10 
 *
 */
public class NodeControl
{
    boolean multipleSelection;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private StackPane nodeStack;
    
    public NodeControl(StackPane node){
        this.nodeStack = node;
        this.nodeStack.setOnMousePressed(this::onMousePressed);
        this.nodeStack.setOnMouseDragged(this::onMouseDragged);
        this.nodeStack.setOnMouseReleased(this::onMouseReleased);
    }
    
    public void onMousePressed(MouseEvent evt){
        this.nodeStack.parentProperty().get().toFront();
        orgSceneX = evt.getSceneX();
        orgSceneY = evt.getSceneY();
        orgTranslateX = this.nodeStack.getTranslateX();
        orgTranslateY = this.nodeStack.getTranslateY();
        if(!this.multipleSelection){
            this.nodeStack.getStyleClass().clear();
            this.nodeStack.getStyleClass().add("selected-node");
        }
        
        changes.firePropertyChange("activeNode", false, true);
    }
    
    public void onMouseDragged(MouseEvent evt){
        double offsetX = evt.getSceneX() - this.orgSceneX;
        double offsetY = evt.getSceneY() - this.orgSceneY;
        double newTranslateX = this.orgTranslateX + offsetX;
        double newTranslateY = this.orgTranslateY + offsetY;
        
        this.nodeStack.setTranslateX(newTranslateX);
        this.nodeStack.setTranslateY(newTranslateY);
    }
    
    public void onMouseReleased(MouseEvent evt){
        
        if(!this.multipleSelection && !evt.isSecondaryButtonDown()){
            this.nodeStack.getStyleClass().clear();
            this.nodeStack.getStyleClass().add("node");
        }
        
        double x = this.nodeStack.getTranslateX();
        double y = this.nodeStack.getTranslateY();

        changes.firePropertyChange("layoutPos", new Point(x, y), new Point(this.nodeStack.getLayoutX() + x, this.nodeStack.getLayoutY() + y));
        this.nodeStack.setTranslateX(0);
        this.nodeStack.setTranslateY(0);
        changes.firePropertyChange("activeNode", true, false);
        
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }   
    
    public void setMultipleSelection(boolean ms){
        this.multipleSelection = ms;
    }
    
    public boolean getMultipleSelection(){
        return this.multipleSelection;
    }
}
