// AUTEUR : Rached, groupe 3
package tpjava3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public class Popup extends Parent {

    public Popup() {

    }

    public boolean verifLogin(String s) {

        String mdpValue = null;
        String key = "MotDePasse";

        try (FileReader in = new FileReader("TP3PropertiesIn")) {
            Properties mdpProp = new Properties();
            mdpProp.load(in);

         //   mdpValue = mdpProp.getProperty(key);
           // System.out.println(key + " = " + mdpProp.getProperty(key));
System.out.println("test");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Popup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Popup.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  System.out.println("s : " + s);

        return true;

    }

    public void login() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Authentification");
        dialog.setContentText("Mot de passe :");

        Optional<String> login = dialog.showAndWait();
        if (login.isPresent()) {
            //     System.out.println("Mot de passe : " + login.get());
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

    public void alerModif() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Modification effectuée !");
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
