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
    public void authSuccessEventListener(AuthenticationSuccessEvent successEvent){

        log.info("---- SuccessEvent ----");
        log.info("Event : " + successEvent.getAuthentication().getPrincipal());
        log.info("Details: " + successEvent.getAuthentication().getDetails());
    }

    @EventListener
    public void authFailedEventListener(AbstractAuthenticationFailureEvent failureEvent){
    	log.info("---- FailureEvent ----");
    	log.info("Event : " + failureEvent.getAuthentication().getPrincipal());
    	log.info("Details: " + failureEvent.getAuthentication().getDetails());
    }
}
