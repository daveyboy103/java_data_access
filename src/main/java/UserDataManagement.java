import java.sql.SQLException;
import java.util.List;

public interface UserDataManagement {
    void update(UserDao user) throws SQLException;
    void delete(UserDao user);
    UserDao create(UserDao user) throws SQLException;
    UserDao retrieve(String userName) throws SQLException;
    UserDao retrieve(int id) throws SQLException;
    List<UserDao> List() throws SQLException, ClassNotFoundException;
}
