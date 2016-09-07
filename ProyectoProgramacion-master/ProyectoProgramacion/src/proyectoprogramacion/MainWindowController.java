/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import objetos.TabGrafo;
import objetos.Grafo;
import java.io.File;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objetos.Arista;

/**
 *
 * @author Raimundo, Jesus Moris
 * @version 2016/08/15
 */
public class MainWindowController 
{
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    ArrayList<Grafo> grafos;
    Stage stage;
    @FXML
    private MenuItem menuAbrir;
    @FXML
    private Button fileButton;
    @FXML
    private Button btnCirculo;
    @FXML
    private Button btnCuadrado;
    @FXML
    private Button btnArista;
    @FXML
    private TabPane tabGrafos;
    
    public MainWindowController()
    {
        grafos = new ArrayList<Grafo>();
    }
   
    
    @FXML
    private void initialize()
    {
        btnCirculo.setOnMouseClicked(this::crearCirculo);
        btnCuadrado.setOnMouseClicked(this::crearCuadrado);
        btnArista.setOnMouseClicked(this::crearArista);
    }
    
    private void crearArista(MouseEvent e)
    {
        /*TabGrafo actualTab = (TabGrafo)tabGrafos.getSelectionModel().getSelectedItem();
        if(actualTab != null)
        {
            System.out.println("Se intento crear arista");
            Arista arista = new Arista(actualTab.getNodo(0), actualTab.getNodo(1));
            actualTab.agregarArista(arista);
        }*/
    }
    
    private void crearCirculo(MouseEvent e){
        TabGrafo actualTab = (TabGrafo)tabGrafos.getSelectionModel().getSelectedItem();
        if(actualTab != null)
            actualTab.agregarNodo(true, 50, 50, 50);
    }
    
    private void crearCuadrado(MouseEvent e)
    {
       TabGrafo actualTab = (TabGrafo)tabGrafos.getSelectionModel().getSelectedItem();
       if(actualTab != null)
        actualTab.agregarNodo(false, 50, 50, 150); 
    }
    
    
    
    @FXML
    public void nuevoGrafo(Event event)
    {
        /* Parte logica */
        Grafo nuevoGrafo = new Grafo();
        nuevoGrafo.setId(grafos.size());
        nuevoGrafo.setLabel("Grafo " + grafos.size());
        grafos.add(nuevoGrafo);
        /* Parte visual */
        TabGrafo nuevoTab = new TabGrafo(nuevoGrafo, tabGrafos.getWidth(), tabGrafos.getHeight());
        nuevoTab.setOnCloseRequest(this::onCloseRequest);
        tabGrafos.getTabs().add(nuevoTab);
        tabGrafos.getSelectionModel().select(nuevoTab);
    }
    
    public void onCloseRequest(Event event)
    {
        TabGrafo tab = (TabGrafo)event.getSource();
        System.out.println("Se intento cerrar el tabulador " + tab.idProperty().getValue());
    }
    
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
    
    @FXML
    public void openFile(Event event) 
    {
        System.out.println("Se inicia el evento y abre selector de ficheros.");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MumoGrafo (*.mmg)", "*.mmg");
        fileChooser.setTitle("Seleccione grafo ");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(this.stage);
        if(file != null)
        {
            System.out.println("Se carga el fichero y se obtiene su informacion.");
            System.out.println("\nInformación del archivo\n---------------------------\nNombre: " + file.getName() + "\nTamaño: " + file.length() + "\nRuta: " + file.getAbsolutePath() + "\n---------------------------\n");
        }
    }
     

}
