package pl.entpoint.harmony.service.settings.userSection;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.settings.UserSection;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

@Service
public class UserSectionServiceImpl implements UserSectionService {

	Date date;
	UserSectionRepository userSectionRepository;
	
	@Autowired
	public UserSectionServiceImpl(UserSectionRepository userSectionRepository) {
		this.userSectionRepository = userSectionRepository;
	}

	@Override
	public List<UserSection> getAllActive() {
		date = new Date(System.currentTimeMillis());
		
		return userSectionRepository.findByExpiredGreaterThanEqual(date);
	}

	@Override
	public UserSection getAllActiveLider(String liderName) {
		date = new Date(System.currentTimeMillis());
		
		return userSectionRepository.findByLiderAndExpiredGreaterThanEqual(liderName, date);
	}

	@Override
	public void save(UserSection section) {
		userSectionRepository.save(section);
	}

	@Override
	public void change(Map<String, String> section) {
		UserSection userSection = userSectionRepository.findById(Long.parseLong(section.get("id")))
				.orElseThrow(() -> new RuntimeException("Nie znaleziono podanej sekcji."));
		
		userSection.setName(section.get("name"));
		userSection.setExpired(Date.valueOf(section.get("expired")));
		userSection.setLider(section.get("lider"));
		
		userSectionRepository.save(userSection);
	}

	@Override
	public void delete(UserSection section) {
		userSectionRepository.delete(section);
	}
}
