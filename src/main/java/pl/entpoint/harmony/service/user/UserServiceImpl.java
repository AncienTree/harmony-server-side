package pl.entpoint.harmony.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.entity.user.enums.Roles;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public User getUser(Long theId) {
        Optional<User> result = userRepository.findById(theId);

        User theUser;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new IllegalArgumentException("Nie znaleziono użytkownika pod ID - " + theId);
        }
        return theUser;
    }

    @Override
    public void changeStatus(Long id, boolean status) {
        Optional<User> result = userRepository.findById(id);

        User theUser;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new IllegalArgumentException("Nie znaleziono użytkownika pod ID - " + id);
        }
        theUser.setStatus(status);
        userRepository.save(theUser);
    }

    @Override
    public User getUserByLogin(String login) {
        Optional<User> result = Optional.ofNullable(userRepository.findByLogin(login));

        User theUser;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new IllegalArgumentException("Nie znaleziono użytkownika pod loginem - " + login);
        }
        return theUser;
    }
}
	
	
