/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjava3;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author stag
 */
public class Dessin extends Tab {

    Dessin() {

        BorderPane tabDessin = new BorderPane();
        // Création d'un canevas pour avoir une zone de dessin
        final Canvas canvas = new Canvas(500, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Création d'une ToolBar pour ajouter les outils couleur, forme et effacer
        ToolBar outils = new ToolBar();
        outils.setOrientation(Orientation.VERTICAL);
        //=== Début de la partie couleur de la ToolBar
        Label couleurLab = new Label("Couleur");
        outils.getItems().add(couleurLab);
        // Création des RadioButton
        RadioButton rouge = new RadioButton("Rouge");
        RadioButton vert = new RadioButton("Vert");
        RadioButton bleu = new RadioButton("Bleu");
        ToggleGroup couleurs = new ToggleGroup();
        rouge.setToggleGroup(couleurs);
        vert.setToggleGroup(couleurs);
        bleu.setToggleGroup(couleurs);
        outils.getItems().addAll(rouge, vert, bleu);
        //=== Fin de la partie couleur de la ToolBar
        //=== Début de la partie Forme de la ToolBar
        Label formeLab = new Label("Forme");
        outils.getItems().add(formeLab);
        // Création des RadioButton
        RadioButton carre = new RadioButton("Carré");
        RadioButton rond = new RadioButton("Rond");
        ToggleGroup forme = new ToggleGroup();
        carre.setToggleGroup(forme);
        rond.setToggleGroup(forme);
        outils.getItems().addAll(carre, rond);
        //=== Fin de la partie Forme de la ToolBar
        Button effacer = new Button("Effacer");
        outils.getItems().add(effacer);

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent t) -> {
            gc.setFill(Color.BLACK);
            gc.fillRect(t.getX(), t.getY(), 5, 5);

        });
        //Rectangle rect = new Rectangle(100, 100);
        tabDessin.setLeft(outils);
        tabDessin.setCenter(canvas);
        this.setContent(tabDessin);
        this.setText("Dessin");
    }
}
