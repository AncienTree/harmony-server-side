package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 17.10.2020
 */

@Data @NoArgsConstructor
public class DayOffPojo {
    private Long id;
    private LocalDate date;
    private String info;
}
