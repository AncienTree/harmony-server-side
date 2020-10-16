package pl.entpoint.harmony.service.user;

import java.util.List;

import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.entity.user.enums.Roles;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

public interface UserService {
    User getUser(Long theId);
    User getUserByLogin(String login);
    List<User> getUsers();
    List<User> getUsersByRoles(Roles role);
    void changeStatus(Long id, boolean status);
    void createUser(User user);
}
