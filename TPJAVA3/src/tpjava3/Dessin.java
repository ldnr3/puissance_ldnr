/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjava3;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author stag
 */
public class Dessin extends Tab {

    Dessin() {
        GridPane contenu = new GridPane();
        Pane zoneDessin = new Pane();
        
        Rectangle rect = new Rectangle(50, 300);
        
        zoneDessin.setOnMouseDragged(e -> {
            double drawx = e.getX();
            double drawy = e.getY();
            
            Circle circle = new Circle(drawx, drawy, 20);
            zoneDessin.getChildren().add(circle);
        });
        contenu.setHgap(5);
        contenu.setVgap(5);

        contenu.setGridLinesVisible(true);
        //contenu.add(zoneDessin, 1, 0, 200, 200);
        //contenu.add(rect, 0, 0);
        this.setContent(contenu);
        this.setText("Dessin");
    }
}
