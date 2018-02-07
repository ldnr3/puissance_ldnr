package DAO;

import connection.MySQLConnection;
import java.sql.Connection;

public abstract class DAO<T> {

    protected Connection connection = MySQLConnection.getInstance();

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return
     */
    public abstract T find(Long id);

    /**
     * Permet de créer une entrée dans la base de données par rapport à un objet
     *
     * @param obj
     * @return
     */
    public abstract T create(T obj);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     *
     * @param obj
     * @return
     */
    public abstract T update(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     *
     * @param obj
     */
    public abstract void delete(T obj);
}
