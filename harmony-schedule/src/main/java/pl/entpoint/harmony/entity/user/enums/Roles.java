package pl.entpoint.harmony.entity.user.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Mateusz Dąbek
 * @created 14/11/2019
 */


public enum Roles implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_SPEC("SPEC"),
    ROLE_MANAGER("MANAGER"),
    ROLE_HR("HR"),
    ROLE_ADMIN("ADMIN");

    public final String name;

    Roles(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name;
    }
}
