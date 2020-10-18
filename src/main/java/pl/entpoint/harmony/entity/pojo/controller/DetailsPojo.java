package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 18.10.2020
 */

@Data @NoArgsConstructor
public class DetailsPojo {
    private Long id;
    private String ltLogin;
    private String ltId;
    private String crmLogin;
    private LocalDate crmAccountExpirationDate;
    private String userSection;
    private String userLine;
    private String fte;
    private String fteStart;
    private String goal1;
    private String goal2;
    private String goal3;
    private String goal4;
    private String goal5;
}
