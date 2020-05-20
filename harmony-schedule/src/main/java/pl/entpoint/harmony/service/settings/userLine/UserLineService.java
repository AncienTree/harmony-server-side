package pl.entpoint.harmony.service.settings.userLine;

import java.util.List;
import java.util.Map;

import pl.entpoint.harmony.entity.settings.UserLine;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

public interface UserLineService {
	
	List<UserLine> getAll();
	UserLine getByName(String name);
	void save(UserLine line);
	void change(Map<String, String> line);
	void delete(UserLine line);

}
