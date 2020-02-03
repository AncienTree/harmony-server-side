package pl.entpoint.harmony.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.entpoint.harmony.util.exception.UserNotActivatedException;

import java.util.Collections;


/**
 * @author Mateusz Dąbek
 * @created 22/01/2020
 */
@Service
@Slf4j
public class CustomDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating {}", username);

        User user = userRepository.findByLogin(username);

        if (!user.isStatus()){
            throw new UserNotActivatedException("Użytkownik " + username +" jest nie aktywny");
        }
        GrantedAuthority auth = new SimpleGrantedAuthority(user.getRole().getAuthority());

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), Collections.singleton(auth));
    }

}
