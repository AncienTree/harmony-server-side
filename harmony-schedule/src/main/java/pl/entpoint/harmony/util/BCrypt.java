package pl.entpoint.harmony.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mateusz Dąbek
 * @created 15/01/2020
 */

@Slf4j
public class BCrypt {

    public static String encrypt(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("Decrypt password");

        return encoder.encode(pass);
    }
}
