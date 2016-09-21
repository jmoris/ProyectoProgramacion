/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-09 
 *
 */
public class RubberBandSelection
{
    private SelectionModel selectionModel;
    private Pane group;
    private DragContext dragContext;
    private Rectangle rect;
    private boolean active;
    
    /**
     * Constructor para la banda de seleccion.
     * @param group 
     */
    public RubberBandSelection(Pane group){
        this.group = group;
        this.selectionModel = new SelectionModel();
        dragContext = new DragContext();
        
        rect = new Rectangle(0,0,0,0);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(1);
        rect.setStrokeLineCap(StrokeLineCap.ROUND);
        rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));
        
        this.group.setOnMousePressed(this::alPresionarMouse);
        this.group.setOnMouseReleased(this::alSoltarMouse);
        this.group.setOnMouseDragged(this::alArrastrarMouse);
    }
    /**
     * Evento que controla el presionar el mouse
     * @param event 
     */
    private void alPresionarMouse(MouseEvent event)
    {
        Node node = null;
        Graph graph = (Graph)this.group;
        for(int i = 0; i < graph.nodesCount(); i++){
            node = graph.getNode(i);
            if(node.getActive()){
                this.active = node.getActive();
            }
        }
        if(!this.active){
            dragContext.mouseAnchorX = event.getX();
            dragContext.mouseAnchorY = event.getY();

            rect.setX(dragContext.mouseAnchorX);
            rect.setY(dragContext.mouseAnchorY);
            rect.setWidth(0);
            rect.setHeight(0);

            this.group.getChildren().add( rect);
        }else{
            for (Node element : selectionModel.selection) {
                element.control.setMultipleSelection(true);
                repaint((StackPane)element.getChildrenUnmodifiable().get(0));
                element.control.onMousePressed(event);
            }
        }
        event.consume();
    }
    /**
     * Metodo que repinta el cuadrado del nodo, ya que al presionar el nodo se cambio a color rojo
     * por presion individual.
     * @param node 
     */
    private void repaint(StackPane node){
        node.getStyleClass().clear();
        node.getStyleClass().add("selected-node");
    }
    /**
     * Metodo que controla el soltar el boton del mouse
     * @param event 
     */
    private void alSoltarMouse(MouseEvent event)
    {
        if(!this.active){
            
                if( !event.isShiftDown() && !event.isControlDown()) {
                    for (Node element : selectionModel.selection) {
                        element.control.setMultipleSelection(false);
                    }
                    selectionModel.clear();
                }
                
                Graph graph = (Graph)this.group;
                for(int i = 0; i < graph.nodesCount(); i++){
                    Node node = graph.getNode(i);       
                    if( node instanceof Node) {
                        if( node.getBoundsInParent().intersects( rect.getBoundsInParent())) {

                            if( event.isShiftDown()) {

                                selectionModel.add( node);

                            } else if( event.isControlDown()) {

                                if( selectionModel.contains( node)) {
                                    selectionModel.remove( node);
                                } else {
                                    selectionModel.add( node);
                                }
                            } else {
                                selectionModel.add( node);
                            }

                        }
                    }

                }

            selectionModel.log();
            rect.setX(0);
            rect.setY(0);
            rect.setWidth(0);
            rect.setHeight(0);

            this.group.getChildren().remove( rect);

            event.consume();
        }else{
            for (Node element : selectionModel.selection) {
                element.control.onMouseReleased(event);
            }            
        }
        this.active = false;
    }
    /**
     * Metodo que controla el arrastre del mouse
     * @param event 
     */
    private void alArrastrarMouse(MouseEvent event)
    {
        if(!this.active){
            double offsetX = event.getX() - dragContext.mouseAnchorX;
            double offsetY = event.getY() - dragContext.mouseAnchorY;

            if( offsetX > 0)
                rect.setWidth( offsetX);
            else {
                rect.setX(event.getX());
                rect.setWidth(dragContext.mouseAnchorX - rect.getX());
            }

            if( offsetY > 0) {
                rect.setHeight( offsetY);
            } else {
                rect.setY(event.getY());
                rect.setHeight(dragContext.mouseAnchorY - rect.getY());
            }
        }else{
            for (Node node : selectionModel.selection) {
                node.control.onMouseDragged(event);
            }
        }
        event.consume(); 

    }    
    /**
     * Metodo para fijar si hay nodos activos
     * @param active 
     */
    public void setActive(boolean active){
        this.active = active;
    }
    /**
     * Metodo para obtener la existencia de nodos activos
     * @return 
     */
    public boolean getActive(){
        return this.active;
    }
    /**
     * Objeto local para guardar posicion del mouse
     */
    private final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
    }    
}
