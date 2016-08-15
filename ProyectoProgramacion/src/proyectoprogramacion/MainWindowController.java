/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import java.io.File;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Raimundo, Jesus Moris
 * @version 2016/08/15
 */
public class MainWindowController
{
    Stage stage;
    @FXML
    private Button fileButton;
    
    @FXML
    private MenuItem menuFile;
    
    @FXML
    private void initialize()
    {
        
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
