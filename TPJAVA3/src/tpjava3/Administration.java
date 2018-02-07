/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjava3;

import DAO.DAO;
import DAO.DAOFactory;
import beans.QuestionBDD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Administration extends Tab {

    private final TextField chReponse;
    private final TextField chQuestion;
    private final RadioButton chNiveau1;
    private final RadioButton chNiveau2;
    private final ComboBox chNum;
    private final Button btnEnregistrer;
    private final Button btnModifier;
    private int valNiv;

    public Administration() {
        this.setText("Administration");

        //selection n° question
        ObservableList<String> numQuestion
                = FXCollections.observableArrayList(
                        "Question n°",
                        "1",
                        "2",
                        "3"
                );
        this.chNum = new ComboBox(numQuestion);
        chNum.getSelectionModel().select(0);
        HBox numHb = new HBox();
        numHb.getChildren().addAll(this.chNum);
        numHb.setAlignment(Pos.CENTER);

        // RadioButton des niveaux
        final ToggleGroup group = new ToggleGroup();
        this.chNiveau1 = new RadioButton("Niveau 1");
        this.chNiveau2 = new RadioButton("Niveau 2");
        chNiveau1.setToggleGroup(group);
        chNiveau2.setToggleGroup(group);
        chNiveau1.setSelected(true);
        VBox nhb = new VBox();
        nhb.getChildren().addAll(this.chNiveau1, this.chNiveau2);
        nhb.setSpacing(5);
        nhb.setAlignment(Pos.CENTER);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                RadioButton niv = (RadioButton) t1.getToggleGroup().getSelectedToggle();
                if (niv.getText().equals("Niveau 1")) {
                    valNiv = 1;
                } else {
                    valNiv = 2;

                }
                //   System.out.println("Selected Radio Button - " + niv.getText());

            }
        });

        // champs de saisie question/reponse
        Label qLabel = new Label("Question : ");
        this.chQuestion = new TextField("");
        chQuestion.setPrefWidth(400);
        HBox qhb = new HBox();
        qhb.getChildren().addAll(qLabel, this.chQuestion);
        qhb.setSpacing(5);
        qhb.setAlignment(Pos.CENTER);

        Label rLabel = new Label("Réponse : ");
        this.chReponse = new TextField("");
        chReponse.setPrefWidth(400);
        HBox rhb = new HBox();
        rhb.getChildren().addAll(rLabel, this.chReponse);
        rhb.setSpacing(5);
        rhb.setAlignment(Pos.CENTER);

        // boutons
        this.btnEnregistrer = new Button("Enregister");
        this.btnModifier = new Button("Modifier");
        HBox bhb = new HBox();
        bhb.getChildren().addAll(this.btnEnregistrer, this.btnModifier);
        bhb.setSpacing(20);
        bhb.setAlignment(Pos.CENTER);

        VBox vb = new VBox();
        vb.getChildren().addAll(numHb, chNum, nhb, qhb, rhb, bhb);
        vb.setSpacing(25);
        vb.setAlignment(Pos.CENTER);

        BorderPane bp = new BorderPane();

        bp.setCenter(vb);

        this.setContent(bp);

        // TRAITEMENT BOUTONS
        btnEnregistrer.setOnAction(event -> {
            DAO<QuestionBDD> questiondao = DAOFactory.getQuestionDAO();
            QuestionBDD quest = questiondao.create(
                    new QuestionBDD(null, this.chQuestion.getText(), this.chReponse.getText(), valNiv));
            System.out.println("question créé : " + quest);

        });

    }

}
