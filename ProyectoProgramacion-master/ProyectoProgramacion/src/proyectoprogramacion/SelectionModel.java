/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import javafx.scene.Node;
import java.util.ArrayList;
/**
 *
 * @author Raimundo
 */
class SelectionModel {
    
    private ArrayList<Node> s;

    public SelectionModel() {
        this.s = new ArrayList<>();          
    }

    public boolean add(Node e) {
        return s.add(e);
    }
    
}
