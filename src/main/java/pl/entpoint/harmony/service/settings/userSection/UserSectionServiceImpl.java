package pl.entpoint.harmony.service.settings.userSection;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.pojo.controller.SectionsPojo;
import pl.entpoint.harmony.entity.settings.UserSection;
import pl.entpoint.harmony.util.exception.setting.UserSectionNotFoundException;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

@Service
public class UserSectionServiceImpl implements UserSectionService {
	LocalDate date;
	private final UserSectionRepository userSectionRepository;

	public UserSectionServiceImpl(UserSectionRepository userSectionRepository) {
		this.userSectionRepository = userSectionRepository;
	}

	@Override
	public UserSection getAllActiveLeader(String leaderName) {
		date = LocalDate.now();

		return userSectionRepository.findByLeaderAndExpiredGreaterThanEqual(leaderName, date);
	}

	@Override
	public UserSection getSectionById(Long id) {
		return userSectionRepository.findById(id)
				.orElseThrow(() -> new UserSectionNotFoundException(id));
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
	public boolean checkSection(UserSection section) {
		date = LocalDate.now();
		Optional<UserSection> userSection = Optional.ofNullable(userSectionRepository.findByLeaderAndExpiredGreaterThanEqual(
				section.getLeader(), date));

		return userSection.isPresent();
	}

	@Override
	public void save(UserSection section) {
		userSectionRepository.save(section);
	}

	@Override
	public void change(SectionsPojo section) {
		UserSection userSection = userSectionRepository.findById(section.getId())
				.orElseThrow(() -> new UserSectionNotFoundException(section.getId()));
		
		userSection.setName(section.getName());
		userSection.setExpired(section.getExpired());
		userSection.setLeader(section.getLider());
		
		userSectionRepository.save(userSection);
	}

	@Override
	public void delete(Long id) {
		UserSection userSection = userSectionRepository.findById(id)
				.orElseThrow(() -> new UserSectionNotFoundException(id));
		userSectionRepository.delete(userSection);
	}
}
