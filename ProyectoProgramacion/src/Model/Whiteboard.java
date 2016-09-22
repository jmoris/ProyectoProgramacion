/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.File;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-09 
 *
 */
public class Whiteboard extends ScrollPane
{
    private double offsetX, offsetY;
    private Node newNode;
    private int tool;
    private final double SCALE_DELTA = 1.1;
    private Graph graph;
    /**
     * Constructor de la pizarra
     */
    public Whiteboard(){
        // Creamos una nueva instancia del grafo.
        this.tool = 0; // 0 mover 1 escalar
        // Agregar ventana para generar el grafo con nombre y tamaño definido.
        this.graph = new Graph("Nuevo grafo", 850, 480);
        Group content = new Group(this.graph);
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.setContent(content);
   }
    
    /**
     * Metodo para guardar un grafo a un archivo.
     * @param path 
     */
    public void saveGraph(String path)
    {
        Serializer serializer = new Persister();
        File result = new File(path);

        try {
            for(int i = 0; i < this.graph.nodesCount(); i++){
                Node node = this.graph.getNode(i);
                if(node.getNodeLabel().isEmpty())
                    node.setNodeLabel(" ");
                if(!node.isVisible())
                    this.graph.removeNode(node);
            }
            serializer.write(this.graph, result);
        } catch (Exception ex) {
            Logger.getLogger(Whiteboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo para abrir un grafo desde un archivo.
     * @param path 
     */
    public void openGraph(String path)
    {
        Serializer serializer = new Persister();
        File source = new File(path);

        try {       
            //this.getChildren().removeAll();
            Graph readGraph;
            readGraph = serializer.read(Graph.class, source);
            this.graph = readGraph;
            setContent(this.graph);
            //this.setContent(this.graph);
        } catch (Exception ex) {
            Logger.getLogger(Whiteboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo para agregar un nodo.
     * @param node 
     */
    public void addNode(Node node){
        node.setNodeLabel(inputDialog());
        this.graph.addNode(node);
    }
    /**
     * Metodo para obtener un nodo.
     * @param i
     * @return 
     */
    public Node getNode(int i){
        return this.graph.getNode(i);
    }
    /**
     * Metodo para obtener el grafo.
     * @return 
     */
    public Graph getGraph(){
        return this.graph;
    }
    
    /**
     * Metodo para leer una entrada.
     * @return 
     */
    private String inputDialog(){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Titulo del nodo");
        dialog.setHeaderText("Ingrese la etiqueta del nodo seleccionado.");
        dialog.setContentText("Etiqueta:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return "";
    }
    
}
