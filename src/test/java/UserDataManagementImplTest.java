import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDataManagementImplTest {

    private UserDataManagementImpl udm;

    @BeforeEach
    void testSetup(){
        udm = new UserDataManagementImpl();
    }
    @Test
    void should_return_employee_list() throws SQLException, ClassNotFoundException {

        var ret = udm.List();

        assertTrue(ret != null);
        assertTrue(ret.size() > 1);
    }
    @Test
    void should_add_person() throws SQLException {

        String userName = UUID.randomUUID().toString().substring(0,27);
        UserDao user = new UserDaoImpl(userName, userName + "@gmail.com");

        user = udm.create(user);

        assertTrue(user != null);
        assertEquals(userName, user.getUserName());
    }
}