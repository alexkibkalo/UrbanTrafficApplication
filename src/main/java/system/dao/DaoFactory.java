package system.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * @param <Entity> тип объекта
 * @param <PrimaryKey> тип первичного ключа
 */


public interface DaoFactory<Entity, PrimaryKey extends Serializable> {

    boolean create() throws SQLException;

    Entity read(PrimaryKey primaryKey) throws SQLException;

    void update(Entity model) throws SQLException;

    void delete(Entity model) throws SQLException;

    List<Entity> getAllObjects() throws SQLException;
}
