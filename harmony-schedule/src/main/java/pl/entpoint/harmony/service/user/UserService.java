package pl.entpoint.harmony.service.user;

import java.util.List;

import pl.entpoint.harmony.models.user.User;
import pl.entpoint.harmony.models.user.enums.Roles;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

public interface UserService {

    List<User> getUsers();

    List<User> getUsersByRoles(Roles role);

    void createUser(User user);

    User getUser(int theId);

    void deleteUser(int theId);

    void changeStatus(int id, boolean status);
}
