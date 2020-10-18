package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mateusz Dąbek
 * @created 18.10.2020
 */

@Data @NoArgsConstructor
public class ContactPojo {
    private Long id;
    private String address;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private String contactPhoneNumber;
    private String contactName;
}
