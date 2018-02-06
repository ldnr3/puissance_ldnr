/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjava3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author stag
 */
public class TPJAVA3 extends Application {

    private Popup pop;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 800, 400);

        // Création de la Menubar
        MenuBar menubar = new MenuBar();
        // Creation des menus de la barre
        Menu activite = new Menu("_Activité");
        Menu niveau = new Menu("_Niveau");
        Menu admin = new Menu("_Administration");
        // MenuItem du Menu activite
        MenuItem dessin = new MenuItem("Dessin");
        MenuItem calcul = new MenuItem("Calcul");
        MenuItem question = new MenuItem("Question-Réponse");
        // Assignation du sous menu d'activite
        activite.getItems().addAll(dessin, calcul, question);
        // MenuItem du Menu niveau
        MenuItem niv1 = new MenuItem("Niveau 1");
        MenuItem niv2 = new MenuItem("Niveau 2");
        //Assignation du sous menu de niveau
        niveau.getItems().addAll(niv1, niv2);
        //Assignation du sous menu Connexion
        MenuItem connexion = new MenuItem("Connexion");
        admin.getItems().addAll(connexion);

        // Assignation des menu à la menubar
        menubar.getMenus().addAll(activite, niveau, admin);

        TabPane tabs = new TabPane();
        tabs.getTabs().add(new Dessin());
        tabs.getTabs().add(new Calcul());
        tabs.getTabs().add(new Question());
        tabs.getTabs().add(new Administration());

        root.setTop(menubar);
        root.setCenter(tabs);
        

        primaryStage.setTitle("Jeux d'Enfants");

        connexion.setOnAction(event -> {
            pop = new Popup();
            pop.login();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
