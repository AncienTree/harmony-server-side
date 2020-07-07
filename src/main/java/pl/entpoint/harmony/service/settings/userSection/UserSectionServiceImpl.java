package pl.entpoint.harmony.service.settings.userSection;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

	LocalDate date;
	final UserSectionRepository userSectionRepository;
	
	@Autowired
	public UserSectionServiceImpl(UserSectionRepository userSectionRepository) {
		this.userSectionRepository = userSectionRepository;
	}
	
	@Override
	public List<UserSection> getAll() {
		return userSectionRepository.findAll();
	}

	@Override
	public List<UserSection> getAllActive() {
		date = LocalDate.now();
		
		return userSectionRepository.findByExpiredGreaterThanEqual(date);
	}

	@Override
	public UserSection getAllActiveLider(String liderName) {
		date = LocalDate.now();
		
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
		userSection.setExpired(LocalDate.parse(section.get("expired")));
		userSection.setLider(section.get("lider"));
		
		userSectionRepository.save(userSection);
	}

	@Override
	public void delete(Long id) {
		UserSection userSection = userSectionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Nie znaleziono podanej sekcji."));
		userSectionRepository.delete(userSection);
	}

	@Override
	public boolean checkSection(UserSection section) {
		date = LocalDate.now();
		Optional<UserSection> userSection = Optional.ofNullable(userSectionRepository.findByLiderAndExpiredGreaterThanEqual(
				section.getLider(), date));

		return userSection.isPresent();
	}
}
