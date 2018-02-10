// AUTEURS : FREDERIC ET RACHED

package connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import tpjava3.Popup;

public class MySQLConnection {

    private static String HOST;
    private static String PORT;
    private static String DATABASE;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private static Connection connection;

    private MySQLConnection() {
    }

    public static Connection getInstance() {
        if (connection == null) {
            try (FileReader in = new FileReader("TP3PropertiesIn")) {
                Properties valProp = new Properties();
                valProp.load(in);
                
                HOST = "127.0.0.1";
                PORT = "3306";
                DATABASE = "question";
                URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
                USER = "root";
                PASSWORD = "1234512345";

          /*      HOST = valProp.getProperty("host");
                PORT = "3306";
                DATABASE = valProp.getProperty("database");
                URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
                USER = valProp.getProperty("user");
                PASSWORD = valProp.getProperty("dbpassword");*/
                

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Popup.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Popup.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
