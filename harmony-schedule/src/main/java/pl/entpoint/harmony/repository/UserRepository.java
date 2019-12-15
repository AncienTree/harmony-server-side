package pl.entpoint.harmony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.entity.enums.Roles;

/**
 * @author Mateusz Dąbek
 * Created on Nov 11, 2019
 * m.dabek@entpoint.pl
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findByRole(Roles role);
	public User findByLogin(String login);
}
