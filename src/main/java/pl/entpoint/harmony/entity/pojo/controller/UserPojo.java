package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.entpoint.harmony.entity.user.enums.Roles;

@Data @NoArgsConstructor
public class UserPojo {
    private Long id;
    private String login;
    private String password;
    private boolean status;
    private Roles role;
}
