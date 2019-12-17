package pl.entpoint.harmony.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.authentication.resource.AuthenticationException;
import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.repository.UserRepository;

@Service
public class JwtDatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User theUser = userRepository.findByLogin(username);

		if (theUser == null) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		} else if(theUser.isStatus() == false) {
			throw new AuthenticationException((String.format("USER_NOT_ACTIVATED '%s'.", username)), new RuntimeException());
		} else {		
            return create(theUser);
        }
	}

    public static JwtUserDetails create(User user) {
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new JwtUserDetails(user.getId(), user.getLogin(), user.getPassword(), roles.toString());
    }
}
