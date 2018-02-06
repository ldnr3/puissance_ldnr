
package tpjava3;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
        this.enonce = new Text("La question s'affichera dans cette zone de texte.");
        HBox qhb = new HBox();
        qhb.getChildren().addAll(qLabel,this.enonce);
        qhb.setSpacing(20);
        
        // *** Composant Graphique : Reponse
        
        Label rLabel = new Label("Réponse : ");
        this.reponseUsr = new TextField("Entrer votre réponse ici");
        HBox rhb = new HBox();
        rhb.getChildren().addAll(rLabel,this.reponseUsr);
        rhb.setSpacing(20);
        
        
        
        // *** Bouton Solution
        
        this.solution = new Button("Solution");
        this.verifier = new Button("Verifier");
        this.autreQuestion = new Button("Autre Question");
        HBox ctrlhb = new HBox();
        ctrlhb.setSpacing(100);
        ctrlhb.getChildren().addAll(this.solution, this.verifier, this.autreQuestion);
           
        BorderPane bp = new BorderPane();
            bp.setTop(qhb);
            bp.setCenter(rhb);
            bp.setBottom(ctrlhb);
        
        this.setContent(bp);
        
    }
}
