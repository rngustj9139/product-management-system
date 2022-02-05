package koo.product.management.system.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data // setter, getter, toString
public class MemberSaveForm {

    @NotEmpty
//  @Email // 이메일 형식이 아닌 경우 예외를 던짐
    private String emailFirst;

    @NotEmpty
    private String emailLast;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

}
