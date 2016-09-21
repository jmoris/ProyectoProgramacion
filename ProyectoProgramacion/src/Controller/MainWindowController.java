/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TabGrafo;
import com.sun.glass.ui.Cursor;
import java.awt.Image;
import java.io.File;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-12
 */
public class MainWindowController {

    Stage stage;
    int tabCounter;
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
    @FXML
    private Button btnMover;
    @FXML
    private Button btnEscalar;
    @FXML
    private MenuItem btnAbout;

    public MainWindowController() {
        this.tabCounter = 0;
    }

    @FXML
    private void initialize() {
        btnCirculo.setOnMouseClicked(this::crearCirculo);
        btnCuadrado.setOnMouseClicked(this::crearCuadrado);
        btnArista.setOnMouseClicked(this::crearArista);
    }
   

    private void crearArista(MouseEvent e) {
        /*TabGrafo actualTab = (TabGrafo)tabGrafos.getSelectionModel().getSelectedItem();
        if(actualTab != null)
        {
            System.out.println("Se intento crear arista");
            Arista arista = new Arista(actualTab.getNodo(0), actualTab.getNodo(1));
            actualTab.agregarArista(arista);
        }*/
    }

    private void crearCirculo(MouseEvent e) {
        TabGrafo actualTab = (TabGrafo) tabGrafos.getSelectionModel().getSelectedItem();
        if (actualTab != null) {
            actualTab.agregarNodo(true, 50, 50, 50);
        }
    }

    private void crearCuadrado(MouseEvent e) {
        TabGrafo actualTab = (TabGrafo) tabGrafos.getSelectionModel().getSelectedItem();
        if (actualTab != null) {
            actualTab.agregarNodo(false, 50, 50, 150);
        }
    }

    @FXML
    public void nuevoGrafo(Event event) {
         /* Parte visual */
        TabGrafo nuevoTab = new TabGrafo(this.tabCounter, tabGrafos.getWidth(), tabGrafos.getHeight());
        nuevoTab.setOnCloseRequest(this::onCloseRequest);
        tabGrafos.getTabs().add(nuevoTab);
        tabGrafos.getSelectionModel().select(nuevoTab);
        this.tabCounter++;
    }
    public void onCloseRequest(Event event) {
        TabGrafo tab = (TabGrafo) event.getSource();
        System.out.println("Se intento cerrar el tabulador " + tab.idProperty().getValue());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    //Ventana de lectura de archivo
    public void openFile(Event event) {
        
        System.out.println("Se inicia el evento y abre selector de ficheros.");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MumoGrafo (*.mmg)", "*.mmg");
        fileChooser.setTitle("Seleccione grafo ");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(this.stage);
        if (file != null) {
           // Crea una tab con un constructor distinto, que nombra la Pestaña con el nombre del archivo seleccionado
           TabGrafo fileTab = new TabGrafo(tabGrafos.getWidth(), tabGrafos.getHeight(), file.getName().replace(".mmg", ""));
           fileTab.setOnCloseRequest(this::onCloseRequest);
           fileTab.openGraph(file.getAbsolutePath());
           tabGrafos.getTabs().add(fileTab);
           tabGrafos.getSelectionModel().select(fileTab);
        }
    }
    
    
    @FXML
    // Ventana de guardado de archivo 
    public void saveFile(Event event) {
        System.out.println("Se inicia el evento y abre el guardador de archivos.");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MumoGrafo (*.mmg)", "*.mmg");
        fileChooser.setTitle("Guardar como...");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(this.stage);
        if (file != null) {
            TabGrafo actualTab = (TabGrafo) tabGrafos.getSelectionModel().getSelectedItem();
            if (actualTab != null) {
                actualTab.getGrafo().setGraphName(file.getName().replace(".mmg", ""));
                actualTab.saveGraph(file.getAbsolutePath());
            }
            //CsvFileWriter.writeCsvFile(file.getAbsolutePath());
        }
    }
    @FXML
    public void showAbout(Event event){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("ACERCA DE MumoGrafo");
        
        dialog.setContentText("Mumo Grafo v0.0.1\n" + "Programa para hacer grafos y operar sobre ellos\n"
                + "(C)2016 Proyecto de Programacion Jesus Moris Rodrigo Letelier Raimundo Vásquez");
        dialog.showAndWait();
        
        
    }
}
