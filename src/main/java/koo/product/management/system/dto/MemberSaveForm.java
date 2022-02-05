package koo.product.management.system.dto;

import koo.product.management.system.domain.member.GenderType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data // setter, getter, toString
public class MemberSaveForm {

    //  @Email // 이메일 형식이 아닌 경우 예외를 던짐
    @NotBlank
    private String emailFirst;

    //  @Email // 이메일 형식이 아닌 경우 예외를 던짐
    @NotBlank
    private String emailLast;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    private GenderType gender;

}
