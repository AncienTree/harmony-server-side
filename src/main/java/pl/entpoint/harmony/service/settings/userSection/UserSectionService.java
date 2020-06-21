package pl.entpoint.harmony.service.settings.userSection;

import java.util.List;
import java.util.Map;

import pl.entpoint.harmony.entity.settings.UserSection;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

public interface UserSectionService {
	
	List<UserSection> getAllActive();
	List<UserSection> getAll();
	UserSection getAllActiveLider(String liderName);
	boolean checkSection(UserSection section);
	void save(UserSection section);
	void change(Map<String, String> section);
	void delete(Long id);	
}
