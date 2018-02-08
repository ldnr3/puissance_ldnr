
// AUTEUR : Rached, groupe 3

package tpjava3;

import java.util.Optional;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public class Popup extends Parent {

    public Popup() {

    }

    public boolean verifLogin(String s) {
       //   return (s.equals("toto")) ;
         return true;

    }

    public void login() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Authentification");
        dialog.setContentText("Mot de passe :");

        Optional<String> login = dialog.showAndWait();
        if (login.isPresent()) {
            //  System.out.println("Mot de passe : " + login.get());
            verifLogin(login.get());
        }

    }

    public void alertSauv() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Enregistrement effectué !");
        alert.showAndWait();
    }
    
     public void alertInfo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir tous les champs !");
        alert.showAndWait();
    }
    
}
