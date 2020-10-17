package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 17.10.2020
 */

@Data
public class SectionsPojo {
    private Long id;
    private String name;
    private LocalDate expired;
    private String lider;
}
