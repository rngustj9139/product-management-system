package koo.product.management.system.domain.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;

//  @Email // 이메일 형식이 아닌 경우 예외를 던짐
    private String email;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

}
