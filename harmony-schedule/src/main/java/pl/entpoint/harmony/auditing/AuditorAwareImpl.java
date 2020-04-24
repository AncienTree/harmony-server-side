package pl.entpoint.harmony.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * @author Mateusz Dąbek
 * @created 24 kwi 2020
 * 
 */

public class AuditorAwareImpl implements AuditorAware<String> {
    
	@Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Test Audyt");
    }
}

