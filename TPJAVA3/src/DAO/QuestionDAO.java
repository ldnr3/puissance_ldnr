// Auteurs : Frédéric et Rached
package DAO;

import beans.QuestionBDD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stag
 */
public class QuestionDAO extends DAO<QuestionBDD> {

    private final String TABLE = "question";

    @Override
    public QuestionBDD find(Long id) {
        QuestionBDD question = null;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE id = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                question = new QuestionBDD(
                        id,
                        result.getString("enonce"),
                        result.getString("reponse"),
                        result.getInt("niveau")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }

    @Override
    public QuestionBDD create(QuestionBDD obj) {
        try {
            String req = "INSERT INTO " + TABLE + " (enonce, reponse, niveau) VALUES(?,?, ?, ?)";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(
                    req, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getEnonce());
            pstmt.setString(2, obj.getReponse());
            pstmt.setInt(3, obj.getNiveau());
            // On soumet la requête et on récupère le nombre de lignes créées
            int nbLignesImpactees = pstmt.executeUpdate();
            // On récupère un ResulSet contenant toutes les clés générées
            // Ici une seule, évidemment
            ResultSet rs = pstmt.getGeneratedKeys();
            long last_inserted_id;
            if (rs.first()) { // Si on a des id créés on lit le premier
                last_inserted_id = rs.getInt(1);
                // On récupère l'enregistrement créé
                obj = this.find(last_inserted_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public QuestionBDD update(QuestionBDD obj) {
        try {
            String req = "UPDATE " + TABLE + " SET enonce = ?, reponse = ?, niveau = ?"
                    + " WHERE id = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, obj.getEnonce());
            pstmt.setString(2, obj.getReponse());
            pstmt.setInt(3, obj.getNiveau());
            pstmt.setLong(4, obj.getId());
            pstmt.executeUpdate();
            // On récupère l'enregistrement modifié
            obj = this.find(obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public void delete(QuestionBDD obj) {
        try {
            String req = "DELETE FROM " + TABLE + " WHERE id = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int compter() {
        int nombre = 0;
        ResultSet res;

        try {
            String req = "SELECT * FROM " + TABLE;
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            res = pstmt.executeQuery(req);
            while (res.next()) {
                nombre++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombre;
    }

}
