package pl.entpoint.harmony.service.statistics;

import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.dto.Stats;

import java.security.Principal;

/**
 * @author Mateusz Dąbek
 * @created 06.09.2020
 */

@Service
public class StatsServiceImpl implements StatsService {

    @Override
    public Stats getMyStats(Principal principal) {

        // Hardcoded test
        Stats stats = new Stats();
        stats.setAbsences(1);
        stats.setCurrentMonthAbsences(2);
        stats.setCurrentMonthLeaves(12);
        stats.setCurrentMonthWorkedHours(140);

        return stats;
    }

    @Override
    public Stats getStats(Long id) {
        return null;
    }
}
