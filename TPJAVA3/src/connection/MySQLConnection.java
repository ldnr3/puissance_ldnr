package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {

    /**
     * URL de connection
     */
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "question";  // BDD Rached = "question"
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    /**
     * Nom du user
     */
    private static final String USER = "root";
    /**
     * Mot de passe du user
     */
    private static final String PASSWORD = "1234512345";
    /**
     * Objet Connection
     */
    private static Connection connection;

    private MySQLConnection() {
    }

    /**
     * Méthode qui va nous retourner notre instance et la créer si elle n'existe
     * pas...
     *
     * @return la connexion vers la base de donnée ou null
     */
    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
