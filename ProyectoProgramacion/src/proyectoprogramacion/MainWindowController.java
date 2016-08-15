/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

/**
 *
 * @author Raimundo
 */
public class MainWindowController 
{
    @FXML
    private Button fileButton;
    
    @FXML
    private MenuItem menuFile;
    
    @FXML
    public void openFile(Event event) 
    {
        System.out.println("HOLA");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione grafo ");
        fileChooser.showOpenDialog(((Node)event.getTarget()).getScene().getWindow());
        
    }
     

}
