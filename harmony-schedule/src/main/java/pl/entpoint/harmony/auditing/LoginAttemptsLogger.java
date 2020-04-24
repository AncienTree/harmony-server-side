package pl.entpoint.harmony.auditing;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mateusz Dąbek
 * @created 24 kwi 2020
 * 
 */

@Component
@Slf4j
public class LoginAttemptsLogger {
   
    @EventListener
    public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent){

        log.info("Oauth2 login success");
        log.info("Event : " + authorizedEvent.getAuthentication().getPrincipal());
        log.info("Details: " + authorizedEvent.getAuthentication().getDetails());
    }

    @EventListener
    public void authFailedEventListener(AbstractAuthenticationFailureEvent oAuth2AuthenticationFailureEvent){
        // write custom code here login failed audit.
    	log.info("Oauth2 login Failed");
    	log.info("Event : " + oAuth2AuthenticationFailureEvent.getAuthentication().getPrincipal());
    	log.info("Details: " + oAuth2AuthenticationFailureEvent.getAuthentication().getDetails());
    }
}
