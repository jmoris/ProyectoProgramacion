/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-12
 */
public class ProyectoProgramacion extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainWindow.fxml"));
        root.getStylesheets().add("/Model/Style.css");
        Scene scene = new Scene(root,1000,620);
        
        stage.setScene(scene);
        stage.setTitle("Diagramador Grafos");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
