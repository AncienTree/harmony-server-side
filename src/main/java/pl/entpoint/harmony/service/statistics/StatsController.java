package pl.entpoint.harmony.service.statistics;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Api(tags = "Statistics Controller")
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/me")
    @ApiOperation(value = "Get my statistic", nickname = "Get my statistic")
    public Stats getMyStats(Principal principal){
        return statsService.getMyStats(principal);
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Get someone statistic by id", nickname = "Get someone statistic by id")
    @ApiImplicitParam(name = "id", value = "User id", required = true, dataType = "long", paramType = "path", defaultValue = "1")
    public Stats getStats(@PathVariable Long id){
        return statsService.getSomeoneStats(id);
    }
}
