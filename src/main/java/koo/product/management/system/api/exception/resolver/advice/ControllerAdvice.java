package koo.product.management.system.api.exception.resolver.advice;

import koo.product.management.system.api.exception.UserException;
import koo.product.management.system.api.exception.resolver.exceptionHandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // @ControllerAdvice + @ResponseBody
public class ControllerAdvice {

    /**
     * 이제 예외를 처리하는 코드와 그렇지 않은 코드가 분리 되었음
     * ApiExceptionV2Controller에서 컨트롤러를 호출 가능
     * @ControllerAdvice에 대상을 지정하지 않으면 모든 컨트롤러에 적용된다.
     * 1. 대상을 지정하고 싶으면 @RestControllerAdvice(annotations = ApiExceptionV2Controller.class) 와 같이 지정할 수 있다. (클래스)
     * 2. 대상을 지정하고 싶으면 @RestControllerAdvice("org.example.controllers") 와 같이 지정할 수 있다. (패키지)
     * 3. 대상을 지정하고 싶으면 @RestControllerAdvice(assignableTypes = {ApiExceptionV1Controller.class, ApiExceptionV2Controller.class}) 와 같이 지정할 수 있다. (여러개의 클래스)
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
