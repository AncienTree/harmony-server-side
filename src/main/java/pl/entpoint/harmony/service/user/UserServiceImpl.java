package pl.entpoint.harmony.service.user;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.entity.user.enums.Roles;
import pl.entpoint.harmony.util.exception.user.UserNotFoundException;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUser(Long theId) {
        return userRepository.findById(theId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login))
                .orElseThrow(() -> new UserNotFoundException(login));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRoles(Roles role) {
        return userRepository.findByRole(role);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void changeStatus(Long id, boolean status) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setStatus(status);
        userRepository.save(user);
    }
}
	
	
