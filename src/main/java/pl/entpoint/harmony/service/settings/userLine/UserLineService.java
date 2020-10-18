package pl.entpoint.harmony.service.settings.userLine;

import java.util.List;

import pl.entpoint.harmony.entity.pojo.controller.LinesPojo;
import pl.entpoint.harmony.entity.settings.UserLine;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

public interface UserLineService {
	UserLine getByName(String name);
	UserLine getById(Long id);
	List<UserLine> getAll();
	void save(UserLine line);
	void change(LinesPojo line);
	void delete(Long id);

}
