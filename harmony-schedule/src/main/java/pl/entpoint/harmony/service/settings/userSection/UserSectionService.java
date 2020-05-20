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
	UserSection getAllActiveLider(String liderName);
	void save(UserSection section);
	void change(Map<String, String> section);
	void delete(UserSection section);	
}
