/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

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

/**
 *
 * @author Raimundo, Jesus Moris
 * @version 2016/08/15
 */
public class MainWindowController 
{
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    Punto centro;
    Boolean fijar, medir;
    ArrayList<Grafo> grafos;
    Stage stage;
    @FXML
    private Button fileButton;
    @FXML
    private Button btnCirculo;
    @FXML
    private Button btnCuadrado;
    
    @FXML
    private MenuItem menuFile;
    @FXML
    private TabPane tabGrafos;
    
    public MainWindowController()
    {
        grafos = new ArrayList<Grafo>();
        centro = new Punto();
    }
   
    
    @FXML
    private void initialize()
    {
        btnCirculo.setOnMouseClicked(this::crearCirculo);
        btnCuadrado.setOnMouseClicked(this::crearCuadrado);
        this.fijar = false;
        this.medir = false;
    }
    
    private void crearCirculo(MouseEvent e){
        this.fijar = true;
        TabGrafo actualTab = (TabGrafo)tabGrafos.getSelectionModel().getSelectedItem();
        actualTab.agregarNodo(true, 50, 50, 50);
    }
    
    private void crearCuadrado(MouseEvent e)
    {
       this.fijar = true;
       TabGrafo actualTab = (TabGrafo)tabGrafos.getSelectionModel().getSelectedItem();
       actualTab.agregarNodo(false, 50, 50, 75); 
    }
    
    @FXML
    public void nuevoGrafo(Event event)
    {
        /* Parte logica */
        Grafo nuevoGrafo = new Grafo();
        nuevoGrafo.id = grafos.size();
        nuevoGrafo.label = "Grafo " + grafos.size();
        grafos.add(nuevoGrafo);
        /* Parte visual */
        TabGrafo nuevoTab = new TabGrafo(nuevoGrafo, tabGrafos.getWidth(), tabGrafos.getHeight());
        nuevoTab.setOnCloseRequest(this::onCloseRequest);
        tabGrafos.getTabs().add(nuevoTab);
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
