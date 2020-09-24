package pl.entpoint.harmony.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.entpoint.harmony.entity.pojo.Stats;

import java.security.Principal;

/**
 * @author Mateusz Dąbek
 * @created 06.09.2020
 */

@RestController
@RequestMapping("/api/employee/stats")
@CrossOrigin(origins = "http://localhost:4200")
public class StatsController {
    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/me")
    public Stats getMyStats(Principal principal){
        return statsService.getMyStats(principal);
    }
    
    @GetMapping("/{id}")
    public Stats getStats(@PathVariable Long id){
        return statsService.getSomeoneStats(id);
    }
}
