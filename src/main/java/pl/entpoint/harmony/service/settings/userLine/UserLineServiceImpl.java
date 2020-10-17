package pl.entpoint.harmony.service.settings.userLine;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.pojo.controller.LinesPojo;
import pl.entpoint.harmony.entity.settings.UserLine;
import pl.entpoint.harmony.util.exception.setting.UserLineNotFoundException;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

@Service
@AllArgsConstructor
public class UserLineServiceImpl implements UserLineService {
	private final UserLineRepository userLineRepository;

	@Override
	public UserLine getByName(String name) {
		return userLineRepository.findByName(name);
	}

	@Override
	public UserLine getById(Long id) {
		return userLineRepository.findById(id)
				.orElseThrow(() -> new UserLineNotFoundException(id));
	}

	@Override
	public List<UserLine> getAll() {
		return userLineRepository.findAll();
	}

	@Override
	public void save(UserLine line) {
		userLineRepository.save(line);		
	}

	@Override
	public void change(LinesPojo line) {
		UserLine userLine = userLineRepository.findById(line.getId())
				.orElseThrow(() -> new UserLineNotFoundException(line.getId()));
		
		userLine.setName(line.getName());
		userLineRepository.save(userLine);
	}

	@Override
	public void delete(Long id) {
		UserLine userLine = userLineRepository.findById(id)
				.orElseThrow(() -> new UserLineNotFoundException(id));
		userLineRepository.delete(userLine);
	}

}
