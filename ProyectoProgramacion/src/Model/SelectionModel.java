/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author Jesus Moris, Raimundo Vasquez, Rodrigo Letelier
 * @version 2016-09-11 
 *
 */
public class SelectionModel 
{
        Set<Node> selection = new HashSet<>();

        public void add( Node node) {
            StackPane stack = (StackPane)node.getChildrenUnmodifiable().get(0);
            stack.getStyleClass().clear();
            stack.getStyleClass().add("selected-node");
            selection.add( node);
        }
        
        public void remove( Node node) {
           StackPane stack = (StackPane)node.getChildrenUnmodifiable().get(0);
           stack.getStyleClass().clear();
           stack.getStyleClass().add("node");
           selection.remove( node);
        }

        public void clear() {
            while( !selection.isEmpty()) {
                remove( selection.iterator().next());
            }
        }

        public boolean contains( Node node) {
            return selection.contains(node);
        }

        public int size() {
            return selection.size();
        }

        public void log() {
            System.out.println("Items en modelo:");
            for (Node node : selection) {
                System.out.println("- Nodo " + node.getNodeId());
            }
        }
}
