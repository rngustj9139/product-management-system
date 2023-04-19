package koo.product.management.system.api.exception;

import koo.product.management.system.api.exception.resolver.exceptionHandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자 입니다.");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello" + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {

        private String memberId;
        private String name;

    }

    /**
     * 이곳에서 정의한 ExceptionHandler는 이 컨트롤러 파일에서만 동작한다. 그리고 정상 코드와 예외처리 코드가 하나의 파일에 존재하게 된다. => 이는 단점이므로 @ContollerAdvice 이용하기
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class) // IllegalArgumentException 발생시(자식 예외 클래스 포함, 스프링은 디테일 한것이 우선순위가 높기 때문에 자식예외가 우선순위 높음) ErrorResult 객체가 json으로 응답되게 된다.
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);

        return new ErrorResult("BAD", e.getMessage()); // but HTTP 응답 상태 코드가 200으로 된다. 따라서 @ResponseStatus를 붙여야 한다.
    }

    @ExceptionHandler // 파라미터로 에러 종류를 적을 경우 생략 가능
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());

        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) { // Exception은 최상위 예외, 따라서 위의 두 예외에 해당하지 않는 예외가 발생할 경우 이 함수가 수행됨
        log.error("[exceptionHandler] ex", e);

        return new ErrorResult("EX", e.getMessage());
    }

}
