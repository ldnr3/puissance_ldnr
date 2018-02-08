// AUTEUR : Rached, groupe 3
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
        DAO<QuestionBDD> questiondao = DAOFactory.getQuestionDAO();

        this.setText("Administration");

        //Initialiser liste de selection n° question
        ObservableList<Long> numQuestion
                = FXCollections.observableArrayList();

        QuestionBDD quest1 = new QuestionBDD();
        for (long i = 1; i <= questiondao.compter(); i++) {
            numQuestion.add(i);
        }

        this.chNum = new ComboBox(numQuestion);
        chNum.getSelectionModel().select(0);
        Label nlabel = new Label("Si modification");
        nlabel.setTranslateX(23);
        nlabel.setTranslateY(0);

        HBox numHb = new HBox();
        numHb.getChildren().addAll(this.chNum, nlabel);
        this.chNum.setTranslateX(20);
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
        vb.getChildren().addAll(numHb, nhb, qhb, rhb, bhb);
        vb.setSpacing(25);
        vb.setAlignment(Pos.CENTER);

        BorderPane bp = new BorderPane();

        bp.setCenter(vb);

        this.setContent(bp);

        // TRAITEMENT BOUTON ENREGISTRER
        btnEnregistrer.setOnAction(event -> {
            QuestionBDD quest2 = questiondao.create(
                    new QuestionBDD(null, this.chQuestion.getText(), this.chReponse.getText(), valNiv));
            Popup pop = new Popup();
            if ((!chQuestion.getText().equals("")) && (!chReponse.getText().equals(""))) {
                pop.alertSauv();
                chNiveau1.setSelected(true);
                this.chQuestion.clear();
                this.chReponse.clear();
            } else {
                pop.alertInfo();
                chNiveau1.setSelected(true);
            }

        });

        // TRAITEMENT BOUTON MODIFIER
        btnModifier.setOnAction(event -> {
            QuestionBDD quest3 = new QuestionBDD();
            quest3 = questiondao.find(8L);
            quest3.setEnonce(this.chQuestion.getText());
            quest3.setReponse(this.chReponse.getText());
            quest3.setNiveau(valNiv);
            quest3 = questiondao.update(quest3);
            Popup pop = new Popup();
            if ((!chQuestion.getText().equals("")) && (!chReponse.getText().equals(""))) {
                pop.alerModif();
                chNiveau1.setSelected(true);
                this.chQuestion.clear();
                this.chReponse.clear();
            } else {
                pop.alertInfo();
                chNiveau1.setSelected(true);
            }

        });

        // TRAITEMENT SELECTION N° QUESTION
        chNum.setOnAction(event -> {
            QuestionBDD quest4 = new QuestionBDD();
            quest4 = questiondao.getObj(chNum.getSelectionModel().getSelectedIndex()+1);
         //   chNum.getSelectionModel().select(0);
            this.chQuestion.setText(quest4.getEnonce());
            this.chReponse.setText(quest4.getReponse());

            //  quest4 = questiondao.find(n);
        });

    }

}
