package pl.entpoint.harmony.service.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.entity.user.enums.Roles;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(Roles role);

    User findByLogin(String login);
}
