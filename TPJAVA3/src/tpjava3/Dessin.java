/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjava3;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author stag
 */
public class Dessin extends Tab {

    private Color couleur = Color.BLACK;
    private String forme = "carre";

    Dessin() {

        BorderPane tabDessin = new BorderPane();
        tabDessin.setBackground(new Background(new BackgroundFill(Color.DIMGRAY,
                CornerRadii.EMPTY, Insets.EMPTY)));

        // StackPane pour contenir le canevas (illusion de Background)
        StackPane conteneur = new StackPane();
        conteneur.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        conteneur.setMaxSize(575, 300);
        // Création d'un canevas pour avoir une zone de dessin       
        final Canvas canvas = new Canvas(575, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Création d'une ToolBar pour ajouter les outils couleur, forme et effacer
        ToolBar outils = new ToolBar();
        outils.setEffect(new DropShadow(10, 5, 0, couleur));
        outils.setOrientation(Orientation.VERTICAL);
        //=== Début de la partie couleur de la ToolBar
        Label couleurLab = new Label("Couleur");
        couleurLab.setFont(new Font("Arial", 15));

        couleurLab.setTranslateX(55);
        outils.getItems().add(couleurLab);

        // Création de la sélection des couleurs
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setMinHeight(25);
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                couleur = colorPicker.getValue();
            }
        });

        colorPicker.setValue(couleur);
        colorPicker.getStyleClass().add("split-button");
        outils.getItems().add(colorPicker);
        //=== Fin de la partie couleur de la ToolBar

        //=== Separateur couleur/pinceau
        final Separator sepCouleur = new Separator();
        sepCouleur.setTranslateY(5);
        outils.getItems().add(sepCouleur);

        //=== Début de la partie Pinceau de la ToolBar
        Label formeLab = new Label("Pinceau");
        formeLab.setFont(new Font("Arial", 15));

        formeLab.setTranslateX(55);
        formeLab.setTranslateY(10);
        outils.getItems().add(formeLab);
        // Création des RadioButton pour le choix de la forme du pinceau
        RadioButton carre = new RadioButton("Carré");
        RadioButton rond = new RadioButton("Rond");
        ToggleGroup formeG = new ToggleGroup();

        // ==== Image du choix carre ==== \\
        ImageView carreImageView = new ImageView();
        Image carreImage = new Image(Dessin.class.getResourceAsStream("/images/carre.png"));
        carreImageView.setImage(carreImage);
        carreImageView.setPreserveRatio(true);

        carre.setGraphic(carreImageView);
        carre.setSelected(true);
        carre.setToggleGroup(formeG);

        // ==== Image du choix rond ==== \\
        ImageView rondImageView = new ImageView();
        Image rondImage = new Image(Dessin.class.getResourceAsStream("/images/rond.png"));
        rondImageView.setImage(rondImage);
        rondImageView.setPreserveRatio(true);

        rond.setGraphic(rondImageView);
        rond.setToggleGroup(formeG);
        carre.setTranslateY(8);
        rond.setTranslateY(10);

        outils.getItems().addAll(carre, rond);

        //=== Fin de la partie Pinceau de la ToolBar
        final Separator sepForme = new Separator();
        sepForme.setTranslateY(15);
        outils.getItems().add(sepForme);

        //================ Création du bouton pour enregistrer le dessin =====\\
        Button enregistrement = new Button("Enregistrer");
        enregistrement.setEffect(new DropShadow(5, couleur));
        enregistrement.setFont(new Font("Arial", 15));
        enregistrement.setTranslateX(40);
        enregistrement.setTranslateY(40);

        enregistrement.setOnAction((ActionEvent t) -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter
                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);

            // Popup de dialogue pour selectionner l'emplacement de sauvegarde
            File file;
            file = fileChooser.showSaveDialog(new javafx.stage.Popup());

            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage(600, 300);
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    Logger.getLogger(Dessin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Button effacer = new Button("Effacer");
        effacer.setEffect(new DropShadow(5, couleur));
        effacer.setFont(new Font("Arial", 15));
        effacer.setTranslateX(55);
        effacer.setTranslateY(50);
        outils.getItems().add(enregistrement);
        outils.getItems().add(effacer);

        /**
         * Effacement du contenu du canevas
         */
        effacer.setOnMouseClicked(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });
        // Sélection de la forme du pinceau
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
        conteneur.getChildren().add(canvas);
        tabDessin.setLeft(outils);
        tabDessin.setCenter(conteneur);
        this.setContent(tabDessin);
        this.setText("Dessin");
    }
}
