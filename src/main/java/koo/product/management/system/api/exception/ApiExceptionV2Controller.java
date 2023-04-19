package koo.product.management.system.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiExceptionV2Controller {

    @GetMapping("/api/members/{id}")
    public ApiExceptionController.MemberDto getMember(@PathVariable("id") String id) { // 예외가 발생하면 오류 페이지(html)가 응답으로 옮(문제점) (스프링의 예외 처리와 오류페이지 제공 기능)
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자 입니다.");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new ApiExceptionController.MemberDto(id, "hello" + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {

        private String memberId;
        private String name;

    }

}
