package pl.entpoint.harmony.service.settings.userSection;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.settings.UserSection;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

@Repository
public interface UserSectionRepository extends JpaRepository<UserSection, Long>{
	UserSection findByLiderAndExpiredGreaterThanEqual(String name, LocalDate date);
	List<UserSection> findByExpiredGreaterThanEqual(LocalDate date);
}
