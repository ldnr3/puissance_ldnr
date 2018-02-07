
package tpjava3;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Quizz extends Tab {
    
    private Text enonce;            // Enoncé de la question
    private TextField reponseUsr;   // Reponse de l'utilisateur
    private Button solution;        // Bouton pour demander l'affichage de la solution
    private Button verifier;        // Bouton pour demander la vérification de la réponse donnée
    private Button autreQuestion;   // Bouton pour demander la génération d'une nouvelle question
   
    private QGenerator qgen;          // Generateur de questions
    
    public Quizz(String title) {
        super(title);
        
        this.qgen = new Calcul(1);   // Test -- Générateur de questions arithmetique de niveau 1
        
        // **** Composant Graphique : Question
        
        Label qLabel = new Label("Question : ");
        this.enonce = new Text(this.qgen.getQuestion());
        HBox qhb = new HBox();
        qhb.getChildren().addAll(qLabel,this.enonce);
        qhb.setSpacing(20);
        qhb.setAlignment(Pos.CENTER);
        
        // *** Composant Graphique : Reponse
        
        Label rLabel = new Label("Réponse : ");
        this.reponseUsr = new TextField();
        HBox rhb = new HBox();
        rhb.getChildren().addAll(rLabel,this.reponseUsr);
        rhb.setSpacing(20);
        rhb.setAlignment(Pos.CENTER);
        
         // **** Composant Graphique : Solution
        
        Label sLabel = new Label("Solution : ");
        Text sol = new Text(this.qgen.getReponse());
        HBox shb = new HBox();
        shb.getChildren().addAll(sLabel,sol);
        shb.setSpacing(20);
        shb.setAlignment(Pos.CENTER);
        shb.setVisible(false);
        
        // *** Bouton Solution
        
        this.solution = new Button("Solution");
        this.solution.setOnAction(
            (ActionEvent e) -> {
                System.out.println("Solution demandée");    // debug               
                shb.setVisible(true);
            }
        );
        
        // *** Bouton Verifier
        
        this.verifier = new Button("Verifier");
        this.verifier.setOnAction(
            (ActionEvent e) -> {
                System.out.println("Verification demandée");    // debug
                
                if (this.reponseUsr.getText().equals(this.qgen.getReponse())) {
                    System.out.println("OK");
                    this.reponseUsr.setStyle("-fx-background-color: GREEN;");
                }
                else
                {
                    System.out.println("KO");
                    this.reponseUsr.setStyle("-fx-background-color: RED;");
                }
                
                shb.setVisible(true);               
            }
        );
        
        // *** Bouton Autre
        
        this.autreQuestion = new Button("Autre Question");
        this.autreQuestion.setOnAction(
            (ActionEvent e) -> {
                
                this.qgen.newQuestion(1);  // Debug provisoire : Niveau = 1
                
                this.enonce.setText(this.qgen.getQuestion());
                this.reponseUsr.clear();
                this.reponseUsr.setStyle("-fx-background-color: WHITE;");
                
                sol.setText(this.qgen.getReponse());
                shb.setVisible(false);
                
                System.out.println("Nouveau Calcul");   // Debug
                System.out.println(this.qgen.getQuestion());
            }
        );
        
        HBox ctrlhb = new HBox();
        ctrlhb.setSpacing(20);
        ctrlhb.setAlignment(Pos.CENTER);
        ctrlhb.getChildren().addAll(this.solution, this.verifier, this.autreQuestion);
        
        VBox vb = new VBox();
        vb.getChildren().addAll(qhb,rhb,shb,ctrlhb);
        vb.setSpacing(50);
        vb.setAlignment(Pos.CENTER);
           
        BorderPane bp = new BorderPane();
            
            bp.setCenter(vb);
     
        
        this.setContent(bp);
        
    }
}
