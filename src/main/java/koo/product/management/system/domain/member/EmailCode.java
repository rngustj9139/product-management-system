package koo.product.management.system.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * naver.com : 네이버
 * google.com : 구글
 * daum.net : 다음
 * */
@Data
@AllArgsConstructor
public class EmailCode {

    private String code;
    private String displayName;

}
