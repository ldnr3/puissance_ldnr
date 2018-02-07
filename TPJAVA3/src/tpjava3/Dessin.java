/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjava3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
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
import javafx.scene.shape.Shape;

/**
 *
 * @author stag
 */
public class Dessin extends Tab {

    private Color couleur = Color.BLACK;
    private String forme = "carre";

    Dessin() {

        BorderPane tabDessin = new BorderPane();
        // Création d'un canevas pour avoir une zone de dessin
        final Canvas canvas = new Canvas(600, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Création d'une ToolBar pour ajouter les outils couleur, forme et effacer
        ToolBar outils = new ToolBar();
        outils.setOrientation(Orientation.VERTICAL);
        //=== Début de la partie couleur de la ToolBar
        Label couleurLab = new Label("Couleur");
        outils.getItems().add(couleurLab);
        // Création de la sélection des couleurs
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                couleur = colorPicker.getValue();
            }
        });
        colorPicker.setValue(couleur);
        colorPicker.getStyleClass().add("button");
        outils.getItems().add(colorPicker);
        //=== Fin de la partie couleur de la ToolBar
        //=== Début de la partie Forme de la ToolBar
        Label formeLab = new Label("Forme");
        formeLab.setTranslateY(10);
        outils.getItems().add(formeLab);
        // Création des RadioButton
        RadioButton carre = new RadioButton("Carré");
        RadioButton rond = new RadioButton("Rond");
        ToggleGroup formeG = new ToggleGroup();
        carre.setSelected(true);
        carre.setToggleGroup(formeG);
        rond.setToggleGroup(formeG);
        carre.setTranslateY(6);
        rond.setTranslateY(7);
        
        outils.getItems().addAll(carre, rond);
        //=== Fin de la partie Forme de la ToolBar

        Button effacer = new Button("Effacer");
        effacer.setTranslateY(15);
        outils.getItems().add(effacer);

        /**
         * Effacement du contenu du canevas
         */
        effacer.setOnMouseClicked(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        formeG.selectedToggleProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if (newValue.equals(carre)) {
                    forme = "carre";
                } else {
                    forme = "rond";
                }
            }

        });
        //======= Canvas =======\\
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent t) -> {
            gc.setFill(couleur);
            if (forme.equals("carre")) {
                gc.fillRect(t.getX(), t.getY(), 5, 5);
            } else {
                gc.fillOval(t.getX(), t.getY(), 5, 5);
            }

        });

        //Rectangle rect = new Rectangle(100, 100);
        tabDessin.setLeft(outils);
        tabDessin.setCenter(canvas);
        this.setContent(tabDessin);
        this.setText("Dessin");
    }
}
