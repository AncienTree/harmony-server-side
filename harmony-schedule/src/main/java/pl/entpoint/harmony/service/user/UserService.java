package pl.entpoint.harmony.service.user;

import java.util.List;

import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.entity.user.enums.Roles;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

public interface UserService {

    List<User> getUsers();

    List<User> getUsersByRoles(Roles role);

    void createUser(User user);

    User getUser(Long theId);

    void deleteUser(Long theId);

    void changeStatus(Long id, boolean status);
}
