package DAO;

import connection.MySQLConnection;
import java.sql.Connection;

public abstract class DAO<T> {

    protected Connection connection = MySQLConnection.getInstance();

    public abstract T find(Long id);

    public abstract T create(T obj);

    public abstract T update(T obj);

    public abstract void delete(T obj);

    public abstract int compter();

}
