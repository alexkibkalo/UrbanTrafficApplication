package system.dao.factory;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * @param <Entity> тип объекта
 * @param <PrimaryKey> тип первичного ключа
 */


public interface DaoFactory<Entity, PrimaryKey extends Serializable> {

    boolean create() throws Exception;

    Entity read() throws SQLException;

    List<Entity> getAllObjects() throws SQLException;
}
