package pl.entpoint.harmony.config.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Mateusz Dąbek
 * @created 27.10.2020
 */

@Component
public class RestAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final long expirationTime;
    private final String secret;
    private final UserRepository userRepository;

    public RestAuthSuccessHandler(
            @Value("${jwt.expirationTime}") long expirationTime,
            @Value("${jwt.secret}") String secret,
            UserRepository userRepository) {
        this.expirationTime = expirationTime;
        this.secret = secret;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        // dla innych projektów
        if (principal.getUsername().equals("administrator") || principal.getUsername().equals("kadry")) {
            User authUser = userRepository.findByLogin(principal.getUsername());
            String token = JWT.create()
                    .withClaim("authorities", authUser.getRole().toString())
                    .withClaim("name", "SYSTEM")
                    .withSubject(principal.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .sign(Algorithm.HMAC256(secret));
            response.getOutputStream().print("{\"token\": \"" + token + "\"}");
        } else {
            User authUser = userRepository.findByLogin(principal.getUsername());
            Employee employee = authUser.getEmployee();
            String fullName = employee.getFirstName() + " " + employee.getLastName();

            String token = JWT.create()
                    .withClaim("authorities", authUser.getRole().toString())
                    .withClaim("name", fullName)
                    .withSubject(principal.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .sign(Algorithm.HMAC256(secret));
            response.getOutputStream().print("{\"token\": \"" + token + "\"}");
        }
    }
}
