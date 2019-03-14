package system.dao.factory;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * @param <Entity> тип объекта
 */


public interface DaoFactory<Entity extends Serializable> {

    boolean create(Entity object) throws Exception;

    Entity read() throws SQLException;

    List<Entity> getAllObjects() throws SQLException;
}
