
package tpjava3;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Quizz extends Tab {
    
    private Text enonce;            // Enoncé de la question
    private TextField reponseUsr;   // Reponse de l'utilisateur
    private Button solution;        // Bouton pour demander l'affichage de la solution
    private Button verifier;        // Bouton pour demander la vérification de la réponse donnée
    private Button autreQuestion;   // Bouton pour demander la génération d'une nouvelle question
    
    private Boolean[] ctrlBttnVisibility;  // Definit la visibilité des boutons de contrôle
    private String[] ctrlBttnLabel;        // Label des boutons
   
    private QGenerator qgen;          // Generateur de questions
   
    public Quizz(String title,QGenerator qg, String[] label,Boolean[] visibility) {
        super(title);
        
        this.qgen = qg;   // Test -- Générateur de questions arithmetique de niveau 1
        this.ctrlBttnLabel = label;
        this.ctrlBttnVisibility = visibility;
        
        // **** Composant Graphique : Question
        
        Label qLabel = new Label("Question : ");
        qLabel.setFont(new Font("Arial", 35));
        this.enonce = new Text(this.qgen.getQuestion());
        HBox qhb = new HBox();
        qhb.getChildren().addAll(qLabel,this.enonce);
        this.enonce.setFont(new Font("Arial", 35));
        qhb.setSpacing(20);
        qhb.setAlignment(Pos.CENTER);
        
        // *** Composant Graphique : Reponse
        
        Label rLabel = new Label("Réponse : ");
        rLabel.setFont(new Font("Arial", 35));
        this.reponseUsr = new TextField();
        HBox rhb = new HBox();
        rhb.getChildren().addAll(rLabel,this.reponseUsr);
        this.reponseUsr.setFont(new Font("Arial", 35));
        rhb.setSpacing(20);
        rhb.setAlignment(Pos.CENTER);
        
         // **** Composant Graphique : Solution
        
        Label sLabel = new Label("Solution : ");
        sLabel.setFont(new Font("Arial", 35));
        Text sol = new Text(this.qgen.getReponse());
        sol.setFont(new Font("Arial", 35));
        HBox shb = new HBox();
        shb.getChildren().addAll(sLabel,sol);
        shb.setSpacing(20);
        shb.setAlignment(Pos.CENTER);
        shb.setVisible(false);
        
        // *** Bouton Solution
        
        this.solution = new Button(ctrlBttnLabel[0]);
        this.solution.setFont(new Font("Arial", 35));
        this.solution.setOnAction(
            (ActionEvent e) -> {
                
                // *** Normalisation des chaines de caracteres
                String rusr = normalizeString(this.reponseUsr.getText());
                String rgen = normalizeString(this.qgen.getReponse());
                
                if (rusr.equals(rgen)) {
                    this.reponseUsr.setStyle("-fx-background-color: GREEN;");
                }
                else
                {
                    this.reponseUsr.setStyle("-fx-background-color: RED;");
                }
                
                shb.setVisible(true);
            }
        );
        
        // *** Bouton Verifier
        
        this.verifier = new Button(ctrlBttnLabel[1]);
        this.verifier.setFont(new Font("Arial", 35));
        this.verifier.setOnAction(
            (ActionEvent e) -> {
 
              // *** Normalisation des chaines de caracteres

                String rusr = normalizeString(this.reponseUsr.getText());
                String rgen = normalizeString(this.qgen.getReponse());
                
                if (rusr.equals(rgen)) {
                    this.reponseUsr.setStyle("-fx-background-color: GREEN;");
                }
                else
                {
                    this.reponseUsr.setStyle("-fx-background-color: RED;");
                }
                
                shb.setVisible(true);               
            }
        );
        
        // *** Bouton Autre
        
        this.autreQuestion = new Button(ctrlBttnLabel[2]);
        this.autreQuestion.setFont(new Font("Arial", 35));
        this.autreQuestion.setOnAction(
            (ActionEvent e) -> {
                
                this.qgen.newQuestion();  // Debug provisoire : Niveau = 1
                
                this.enonce.setText(this.qgen.getQuestion());
                this.reponseUsr.clear();
                this.reponseUsr.setStyle("-fx-background-color: WHITE;");
                
                sol.setText(this.qgen.getReponse());
                shb.setVisible(false);
                
//                System.out.println("Nouveau Calcul");   // Debug
//                System.out.println(this.qgen.getQuestion());
            }
        );
        
        // **** Visibilite des boutons de contrôle
        this.solution.setVisible(ctrlBttnVisibility[0]);
        this.verifier.setVisible(ctrlBttnVisibility[1]);
        this.autreQuestion.setVisible(ctrlBttnVisibility[2]);
        
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
    
    private String normalizeString(String src) {
        
        String dst = src.toLowerCase();
              
        // *** {à,â} -> a
        dst = dst.replace('à','a');
        dst = dst.replace('â','a');
        
        // *** {é,è,ê} -> e
        dst = dst.replace('é','e');
        dst = dst.replace('è','e');
        dst = dst.replace('ê','e');
        
        // *** î -> i
        dst = dst.replace('î', 'i');
        
        // *** ô -> o
        dst = dst.replace('ô','o');
        
        // *** {ù,û} -> u
        dst = dst.replace('ù','u');
        dst = dst.replace('û','u');
        
        // *** ç -> c
        dst = dst.replace('ç', 'c');
                
        return dst;
    }
}
