package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mateusz Dąbek
 * @created 18.10.2020
 */

@Data @NoArgsConstructor
public class LeavePojo {
    private Long id;
    private int total;
    private int normal;
    private int uz;
    private int additional;
    private int pastYears;
}
