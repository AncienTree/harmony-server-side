package pl.entpoint.harmony.service.settings.userLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.settings.UserLine;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

@Repository
public interface UserLineRepository extends JpaRepository<UserLine, Long>{
	UserLine findByName(String name);
}
