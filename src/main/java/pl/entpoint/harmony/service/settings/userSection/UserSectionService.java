package pl.entpoint.harmony.service.settings.userSection;

import java.util.List;

import pl.entpoint.harmony.entity.pojo.controller.SectionsPojo;
import pl.entpoint.harmony.entity.settings.UserSection;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

public interface UserSectionService {
	UserSection getAllActiveLeader(String leaderName);
	UserSection getSectionById(Long id);
	List<UserSection> getAllActive();
	List<UserSection> getAll();
	boolean checkSection(UserSection section);
	void save(UserSection section);
	void change(SectionsPojo section);
	void delete(Long id);	
}
