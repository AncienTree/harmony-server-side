package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Size;

/**
 * @author Mateusz Dąbek
 * @created 18.10.2020
 */

@Data @NoArgsConstructor
public class InfoPojo {
    private Long id;
    private boolean agreement;
    private boolean ppk;
    private boolean headphones;
    private String locker;
    private String idCard;
    private String parkingCard;
    private String info1;
    private String info2;
    private String info3;
    private String info4;
}
