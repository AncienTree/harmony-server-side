package pl.entpoint.harmony.util.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import pl.entpoint.harmony.service.employee.EmployeeService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 29/02/2020
 */
public class CustomToken implements TokenEnhancer {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        String fullname = employeeService.getFullNameByLogin(oAuth2Authentication.getName());

        System.out.println(fullname);
        Map<String, Object> infoToken = new HashMap<>();

        // Dodatkowe pola w payload JWT
        infoToken.put("organization", "TODO jakaś nazwa");
        infoToken.put("name", fullname);

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(infoToken);
        return oAuth2AccessToken;
    }
}
