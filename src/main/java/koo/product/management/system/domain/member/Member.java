package koo.product.management.system.domain.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;
    private String email;
    private String loginId;
    private String name;
    private String password;
    private GenderType gender;

}
