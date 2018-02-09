/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjava3;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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

        // *** Onglet Calcul
        String[] tcLabel = {"Solution","","Autre Calcul"};
        Boolean[] tcVisibility = {true,false,true};
        Calcul cgen = new Calcul(1);        // Niveau 1 par defaut
        final Quizz tabCalcul = new Quizz("Calcul",cgen,tcLabel,tcVisibility);
        
        // *** Onglet Question
        String[] tqLabel = {"Solution","Vérifier","Autre Question"};
        Boolean[] tqVisibility = {true,true,true};
        Question qgen = new Question(1);    // Niveau 1 par defaut
        Quizz tabQuestion = new Quizz("Question",qgen,tqLabel,tqVisibility);

        ToggleGroup groupNiveau = new ToggleGroup();
        // MenuItem du Menu niveau
        RadioMenuItem niv1 = new RadioMenuItem("Niveau 1");
        niv1.setUserData(1);
        niv1.setToggleGroup(groupNiveau);
        RadioMenuItem niv2 = new RadioMenuItem("Niveau 2");
        niv2.setUserData(2);
        niv2.setToggleGroup(groupNiveau);
       
        groupNiveau.selectedToggleProperty().addListener(
         (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
        Toggle new_toggle) -> {
        if (groupNiveau.getSelectedToggle() != null) {
            int niv = (int)groupNiveau.getSelectedToggle().getUserData();
//          System.out.println("Niveau sélectionné : " + niv);  // debug
            
            cgen.setNiveau(niv);
            qgen.setNiveau(niv);           
         }
        });
        
        //Assignation du sous menu de niveau
        niveau.getItems().addAll(niv1, niv2);
        //Assignation du sous menu Connexion
        MenuItem connexion = new MenuItem("Connexion");
        admin.getItems().addAll(connexion);

        // Assignation des menu à la menubar
        menubar.getMenus().addAll(activite, niveau, admin);

        TabPane tabs = new TabPane();
        // Personnalisation de la taille des onglets
        tabs.setTabMinWidth(125);
        tabs.setTabMinHeight(30);
        // Création des trois onglets principaux
        Dessin tabDessin = new Dessin();
                
        tabs.getTabs().add(tabDessin);
        tabs.getTabs().add(tabCalcul);
        tabs.getTabs().add(tabQuestion);
        
        // Gestion de la fermeture des onglets
        // L'onglet est désactivé lorsqu'il est fermé
        tabDessin.setOnClosed(e->{
            tabDessin.setDisable(true);
        });
        tabCalcul.setOnClosed(e->{
            tabCalcul.setDisable(true);
        });
        tabQuestion.setOnClosed(e->{
            tabQuestion.setDisable(true);
        });
        // Appel des différents onglets quand cliqué dans le menu
        dessin.setOnAction(e -> {
            // Si l'onglet est désactivé, on le réactive et on l'ouvre
            if (tabDessin.isDisable()) {
                tabDessin.setDisable(false);
                tabs.getTabs().add(tabDessin);
            }
            // L'onglet est sélectionné
            SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
            selectionModel.select(tabDessin);
              
        });
        calcul.setOnAction(e -> {
            // Si l'onglet est désactivé, on le réactive et on l'ouvre
            if (tabCalcul.isDisable()) {
                tabCalcul.setDisable(false);
                tabs.getTabs().add(tabCalcul);
            }
            // L'onglet est sélectionné
            SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
            selectionModel.select(tabCalcul);
              
        });
        question.setOnAction(e -> {
            // Si l'onglet est désactivé, on le réactive et on l'ouvre
            if (tabQuestion.isDisable()) {
                tabQuestion.setDisable(false);
                tabs.getTabs().add(tabQuestion);
            }
            // L'onglet est sélectionné
            SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
            selectionModel.select(tabQuestion);
              
        });
        // Appel de la fenetre popup pour la connexion
        connexion.setOnAction(event -> {
            pop = new Popup();
      /*      pop.login();
            String s = null;
            if (pop.verifLogin(s) == true) {
                tabs.getTabs().add(new Administration());
            } */
        });

        root.setTop(menubar);
        root.setCenter(tabs);

        primaryStage.setTitle("Jeux d'Enfants");
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
