import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static Util.DateHelpers.convertToLocalDateTime;

public class UserDataManagementImpl implements UserDataManagement {

    private int idOrdinal;
    private int idUserNameOrdinal;
    private int uuidOrdinal;
    private int createdOrdinal;
    private int emailOrdinal;

    @Override
    public void update(UserDao user) throws SQLException {


    }

    @Override
    public void delete(UserDao user) {

    }

    @Override
    public UserDao create(UserDao user) throws SQLException {
        try(var conn = getConnection()) {

            String sql = String.format("insert into User(UserName, Uuid, EmailAddress) ");
            sql += String.format("values('%s', '%s', '%s')", user.getUserName(), UUID.randomUUID(), user.getEmail());
            var stmt = conn.createStatement();
            stmt.execute(sql);
            return retrieve(user.getUserName());
        }
    }

    @Override
    public UserDao retrieve(String userName) throws SQLException {
        String sql = getSql(userName);
        return getUserDao(sql);
    }

    @Override
    public UserDao retrieve(int id) throws SQLException {
        String sql = getSql(id);
        return getUserDao(sql);
    }

    private UserDao getUserDao(String sql) throws SQLException {
        try(var conn = getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            resolveResultSetOrdinals(rs);

            UserDaoImpl ret = null;

            while(rs.next()){
                Date date = rs.getDate(createdOrdinal);
                LocalDateTime localDateTime = convertToLocalDateTime(date);
                ret = new UserDaoImpl(
                        rs.getInt(idOrdinal), rs.getString(idUserNameOrdinal),
                        rs.getString(uuidOrdinal), localDateTime,
                        LocalDateTime.now(), rs.getString(emailOrdinal));
            }

            return ret;
        }
    }

    private String getSql(String userName) {
        return String.format("select * from User where UserName ='%s'", userName);
    }

    private String getSql(int id) {
        return String.format("select * from User where Id =%s", id);
    }

    @Override
    public List<UserDao> List() throws SQLException {

        var ret = new ArrayList<UserDao>();

        try(var conn = getConnection()){
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("select * from User");

            resolveResultSetOrdinals(rs);

            while(rs.next())
            {
                Date date = rs.getDate(createdOrdinal);
                LocalDateTime localDateTime = convertToLocalDateTime(date);
                var usr = new UserDaoImpl(
                        rs.getInt(idOrdinal), rs.getString(idUserNameOrdinal),
                        rs.getString(uuidOrdinal), localDateTime,
                        LocalDateTime.now(), rs.getString(emailOrdinal));
                ret.add(usr);
            }
        }

        return ret;
    }

    private void resolveResultSetOrdinals(ResultSet rs) throws SQLException {
        idOrdinal = rs.findColumn("id");
        idUserNameOrdinal = rs.findColumn("UserName");
        uuidOrdinal = rs.findColumn("Uuid");
        createdOrdinal = rs.findColumn("Created");
        emailOrdinal = rs.findColumn("EmailAddress");
    }

    private Connection getConnection(){
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            String pwd = "cabinpw";
            String user = "cabin";
            String connection = "jdbc:mysql://localhost:3306/cabin";
            return DriverManager.getConnection(connection, user, pwd);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
