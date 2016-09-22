/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-10 
 *
 */
@Root(name="Grafo")
public class Graph extends Pane
{
    @Element(name="Nombre")
    private String graphName;
    @Element(name="Ancho")
    private double width;
    @Element(name="Alto")
    private double height;
    @ElementList(name="Nodos")
    private ArrayList<Node> nodeList;
    @ElementList(name="Aristas")
    private ArrayList<Edge> edgeList;
    private RubberBandSelection rubberSelection;
    private boolean nodeIsActive;
    /* Atributos para agregar arista */
    private boolean addingEdge = false;
    private int edgeNumber = 0;
    private Node temp1;
    private Node temp2;
    
    
    public Graph(@Element(name="Nombre")String graphName, @Element(name="Ancho")double width, @Element(name="Alto")double height){
        //Tamaño para ventana actual 850, 480
        this.width = width;
        this.height = height;
        this.setPrefSize(this.width, this.height);
        this.nodeIsActive = false;
        this.nodeList = new ArrayList<Node>();
        this.edgeList = new ArrayList<Edge>();
        this.graphName = graphName;
        
        // Creamos el evento para redimensionar.
       
        this.rubberSelection = new RubberBandSelection(this);
        this.getStyleClass().add("panel");
        this.setOnScroll(this::resize);
    }
    
    public Graph(@Element(name="Nombre")String graphName, @ElementList(name="Nodos")ArrayList<Node> nodes,@ElementList(name="Aristas")ArrayList<Edge> edges,@Element(name="Ancho")double width, @Element(name="Alto")double height){
        this.width = width;
        this.height = height;
        this.setPrefSize(this.width, this.height);
        this.nodeIsActive = false;
        
        this.nodeList = nodes;
        for (Node node : nodes) {
            System.out.println("Nodo " + node.getNodeLabel() + " - posicion " + node.getPos().getPointX() + " ," + node.getPos().getPointY());
            this.getChildren().add(node);
        }
        this.edgeList = edges;
        for (Edge edge : edges) {
            this.getChildren().add(edge);
        }
        // Se refrescan las aristas y se envian atras de los nodos.
        refreshEdges();
        this.graphName = graphName;
        this.getStyleClass().add("panel");
        this.rubberSelection = new RubberBandSelection(this);
        this.setOnScroll(this::resize);
    }    
    
    
    private void resize(ScrollEvent event) {
        double zoomFactor = 1.05f; // De 5% se aumenta y disminuye
        double deltaY = event.getDeltaY();
        if (deltaY < 0){
            zoomFactor = 2.0 - zoomFactor;
        }
        if(zoomFactor*this.getScaleX() > 0.2){
            this.setScaleX(this.getScaleX() * zoomFactor);
            this.setScaleY(this.getScaleY() * zoomFactor);
        }
        event.consume();
    }
    
    public void addNode(Node node){
        this.nodeList.add(node);
        node.addPropertyChangeListener(this::onNodeChange);
        this.getChildren().add(node);
    }
    
    public void addEdge(){
        this.addingEdge = true;
        this.edgeNumber = 1;
    }
    
    private void onNodeChange(PropertyChangeEvent evt){
        String propertyName = evt.getPropertyName();
        if(propertyName == "clickedId"){
            int id = (int)evt.getNewValue();
            System.out.println(id);
            if(addingEdge){
                System.out.println("agregando nodo");
                for (Node node : nodeList) {
                    if(node.getNodeId() == id){
                        if(edgeNumber == 1){
                            System.out.println("nodo 1");
                            this.temp1 = node;
                            edgeNumber++;
                            break;
                        }
                        if(this.temp1 != node && edgeNumber == 2){
                            System.out.println("nodo 2");
                            this.temp2 = node;
                            edgeNumber++;
                            break;
                        }
                    }
                }
                if(this.temp1 != null && this.temp2 != null && edgeNumber == 3){
                    Edge newEdge = new Edge(this.temp1, this.temp2);
                    edgeList.add(newEdge);
                    this.getChildren().add(newEdge);    
                    addingEdge = false;
                    edgeNumber = 0;
                }
            }
        }else if(propertyName == "nodePos"){
            refreshEdges();
        }
    }
    
    private void refreshEdges(){
        for (Edge edge : edgeList) {
            edge.refresh();
            edge.toBack();
        }        
    }
    
    public Node getNode(int i){
        return this.nodeList.get(i);
    }
    
    public void removeNode(Node node){
        this.nodeList.remove(node);
    }
    
    public int nodesCount(){
        return this.nodeList.size();
    }

    public String getGraphName(){
        return graphName;
    }

    public void setGraphName(String graphName){
        this.graphName = graphName;
    }
    
    public void setActive(boolean active){
        this.nodeIsActive = active;
    }
    
    public boolean getActive(){
        return this.nodeIsActive;
    }
}
