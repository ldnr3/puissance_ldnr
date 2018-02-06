
package tpjava3;

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
        
        // **** Composant Graphique : Question
        
        Label qLabel = new Label("Question : ");
        this.enonce = new Text("Enoncé");
        HBox qhb = new HBox();
        qhb.getChildren().addAll(qLabel,this.enonce);
        qhb.setSpacing(20);
        qhb.setAlignment(Pos.CENTER);
        
        // *** Composant Graphique : Reponse
        
        Label rLabel = new Label("Réponse : ");
        this.reponseUsr = new TextField("Entrer votre réponse ici");
        HBox rhb = new HBox();
        rhb.getChildren().addAll(rLabel,this.reponseUsr);
        rhb.setSpacing(20);
        rhb.setAlignment(Pos.CENTER);
        
        // *** Bouton Solution
        
        this.solution = new Button("Solution");
        this.verifier = new Button("Verifier");
        this.autreQuestion = new Button("Autre Question");
        HBox ctrlhb = new HBox();
        ctrlhb.setSpacing(20);
        ctrlhb.setAlignment(Pos.CENTER);
        ctrlhb.getChildren().addAll(this.solution, this.verifier, this.autreQuestion);
        
        VBox vb = new VBox();
        vb.getChildren().addAll(qhb,rhb,ctrlhb);
        vb.setSpacing(50);
        vb.setAlignment(Pos.CENTER);
           
        BorderPane bp = new BorderPane();
            
            bp.setCenter(vb);
     
        
        this.setContent(bp);
        
    }
}
