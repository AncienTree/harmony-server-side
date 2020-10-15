package pl.entpoint.harmony.service.settings.userLine;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.settings.UserLine;

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
	public List<UserLine> getAll() {
		return userLineRepository.findAll();
	}

	@Override
	public UserLine getByName(String name) {
		return userLineRepository.findByName(name);
	}

	@Override
	public void save(UserLine line) {
		userLineRepository.save(line);		
	}

	@Override
	public void delete(Long id) {
		UserLine userLine = userLineRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Nie znaleziono podanej lini."));
		userLineRepository.delete(userLine);		
	}

	@Override
	public void change(Map<String, String> line) {
		UserLine userLine = userLineRepository.findById(Long.parseLong(line.get("id")))
				.orElseThrow(() -> new RuntimeException("Nie znaleziono podanej lini."));
		
		userLine.setName(line.get("name"));
		userLineRepository.save(userLine);
	}	
}
