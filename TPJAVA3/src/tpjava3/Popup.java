package tpjava3;

import java.util.Optional;
import javafx.scene.Parent;
import javafx.scene.control.TextInputDialog;

public class Popup extends Parent {

    public Popup() {

    }

    public void login() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Authentification");
        dialog.setContentText("Mot de passe :");

        Optional<String> login = dialog.showAndWait();
        if (login.isPresent()) {
            System.out.println("Mot de passe : " + login.get());
        }
    }

}
