package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 17.10.2020
 */

@Data @NoArgsConstructor
public class SectionsPojo {
    private Long id;
    private String name;
    private LocalDate expired;
    private String lider;
}
