package pl.entpoint.harmony.service.statistics;

import java.security.Principal;

import pl.entpoint.harmony.entity.pojo.Stats;

/**
 * @author Mateusz Dąbek
 * @created 06.09.2020
 */

public interface StatsService {
    Stats getMyStats(Principal principal);
    Stats getSomeoneStats(Long id);
}
