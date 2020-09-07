package pl.entpoint.harmony.service.statistics;

import pl.entpoint.harmony.entity.dto.Stats;

import java.security.Principal;

/**
 * @author Mateusz Dąbek
 * @created 06.09.2020
 */

public interface StatsService {
    Stats getMyStats(Principal principal);
    Stats getStats(Long id);
}
