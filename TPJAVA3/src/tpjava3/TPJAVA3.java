// AUTEURS : ALEXANDRE, FREDERIC ET RACHED
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.YELLOW;
import javafx.scene.paint.Paint;
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

        Scene scene = new Scene(root, 1200, 800);
        // Création de la Menubar
        MenuBar menubar = new MenuBar();
        // Creation des menus de la barre
        Menu activite = new Menu("_Activité");
        Menu niveau = new Menu("_Niveau");
        Menu admin = new Menu("_Administration");
        // MenuItem du Menu activite
        MenuItem dessin = new MenuItem("Dessin");
        dessin.setAccelerator(
                new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN)
        );
        MenuItem calcul = new MenuItem("Calcul");
        dessin.setAccelerator(
                new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN)
        );
        MenuItem question = new MenuItem("Question-Réponse");
        dessin.setAccelerator(
                new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)
        );
        // Assignation du sous menu d'activite
        activite.getItems().addAll(dessin, calcul, question);

        // *** Onglet Calcul
        String[] tcLabel = {"Solution", "", "Autre Calcul"};
        Boolean[] tcVisibility = {true, false, true};
        Calcul cgen = new Calcul(1);        // Niveau 1 par defaut
        final Quizz tabCalcul = new Quizz("Calcul", cgen, tcLabel, tcVisibility);

        // *** Onglet Question
        String[] tqLabel = {"Solution", "Vérifier", "Autre Question"};
        Boolean[] tqVisibility = {true, true, true};
        Question qgen = new Question(1);    // Niveau 1 par defaut
        Quizz tabQuestion = new Quizz("Question", qgen, tqLabel, tqVisibility);

        ToggleGroup groupNiveau = new ToggleGroup();
        // MenuItem du Menu niveau
        RadioMenuItem niv1 = new RadioMenuItem("Niveau 1");
        niv1.setUserData(1);
        niv1.setToggleGroup(groupNiveau);
        RadioMenuItem niv2 = new RadioMenuItem("Niveau 2");
        niv2.setUserData(2);
        niv2.setToggleGroup(groupNiveau);

        // *** Sélection initiale du niveau : Niveau 1
        groupNiveau.selectToggle(niv1);
        cgen.setNiveau(1);
        qgen.setNiveau(1);

        groupNiveau.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                        Toggle new_toggle) -> {
                    if (groupNiveau.getSelectedToggle() != null) {
                        int niv = (int) groupNiveau.getSelectedToggle().getUserData();
//          System.out.println("Niveau sélectionné : " + niv);  // debug           
                        cgen.setNiveau(niv);
                        qgen.setNiveau(niv);
                    }
                });

        //Assignation du sous menu de niveau
        niveau.getItems().addAll(niv1, niv2);
        //Assignation du sous menu Connexion
        MenuItem connexion = new MenuItem("Connexion");
        MenuItem deconnexion = new MenuItem("Deconnexion");
        admin.getItems().addAll(connexion, deconnexion);
        deconnexion.setDisable(true);
        // Assignation des menu à la menubar
        menubar.getMenus().addAll(activite, niveau, admin);

        // Création de la barre des onglets
        TabPane tabs = new TabPane();
        // Personnalisation de la taille des onglets
        tabs.setTabMinWidth(200);
        tabs.setTabMinHeight(50);
        // Création des trois onglets principaux
        Dessin tabDessin = new Dessin();

        tabs.getTabs().add(tabDessin);
        tabs.getTabs().add(tabCalcul);
        tabs.getTabs().add(tabQuestion);

        // Gestion de la fermeture des onglets
        // L'onglet est désactivé lorsqu'il est fermé
        tabDessin.setOnClosed(e -> {
            tabDessin.setDisable(true);
        });
        tabCalcul.setOnClosed(e -> {
            tabCalcul.setDisable(true);
        });
        tabQuestion.setOnClosed(e -> {
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
        // Onglet Administration crée si login OK
        connexion.setOnAction(event -> {
            pop = new Popup();
            if (pop.login() == true) {
                Administration tabAdmin = new Administration();
                tabs.getTabs().add(tabAdmin);
                // L'onglet est sélectionné
                SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
                selectionModel.select(tabAdmin);
                // sous-menus connexion desactivé et deconnexion activé
                connexion.setDisable(true);
                deconnexion.setDisable(false);

                // onglet Admin fermé -> réactivation connexion et désactivation déconnexion
                tabAdmin.setOnClosed(e -> {
                    selectionModel.select(tabDessin);
                    connexion.setDisable(false);
                    deconnexion.setDisable(true);
                });
            }
        });

        // deconnexion
        deconnexion.setOnAction(event -> {
            start(primaryStage);
        }
        );

        root.setTop(menubar);
        root.setCenter(tabs);

        primaryStage.setTitle("Jeux d'Enfants");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

// MAIN
    public static void main(String[] args) {
        launch(args);
    }

}
