package pl.entpoint.harmony.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.entpoint.harmony.util.exception.user.UserNotActivatedException;

import java.util.Collections;
import java.util.Optional;


/**
 * @author Mateusz Dąbek
 * @created 22/01/2020
 */
@Service
@Slf4j
@AllArgsConstructor
public class CustomDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        log.debug("Uwierzytelnianie {}", username);

        User user = Optional.of(userRepository.findByLogin(username))
                        .orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono użytkownika"));

        if (!user.isStatus()){
            log.debug(String.format("%s - jest nieaktywny w bazie danych.", username));
            throw new UserNotActivatedException();
        }
        GrantedAuthority auth = new SimpleGrantedAuthority(user.getRole().getAuthority());

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), Collections.singleton(auth));
    }

}
