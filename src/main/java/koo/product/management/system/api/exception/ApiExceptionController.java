package koo.product.management.system.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ApiExceptionController {

    private static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    private static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    private static final String ERROR_MESSAGE = "javax.servlet.error.message";
    private static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    private static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    private static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) { // 예외가 발생하면 오류 페이지(html)가 응답으로 옮(문제점) (스프링의 예외 처리와 오류페이지 제공 기능, BasicErrorController가 담당)
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

    @GetMapping("/error-page/500")
    public void errorPageGeneration() {
        throw new RuntimeException("잘못된 요청입니다.");
    }

    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE) // 요청시 Accept를 application/json으로 해야함
    public ResponseEntity<Map<String, Object>> errorPage500Api(HttpServletRequest request, HttpServletResponse response) {
        log.info("API errorPage 500");

        HashMap<String, Object> result = new HashMap<>(); // 해쉬맵은 순서를 보장하지 않는다.
        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
        result.put("status", request.getAttribute(ERROR_EXCEPTION_TYPE));

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {

        private String memberId;
        private String name;

    }

    // 그냥 요청시 accept를 application/json으로 하면 예외가 발생할 때 스프링부트가 기본적으로 제공하는 json 응답을 내려준다.(BasicErrorController가 담당)

    @GetMapping("/api/response-status-ex1") // 그냥 HandlerExceptionResolver를 만들어서 쓰는것 보다는 스프링에서 제공하는 ExceptionResolver를 이용 (예시 - ResponseStatusExceptionResolver)
    public String responseStatusEx1() {
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2") // 그냥 ResponseStatusExceptionResolver를 쓰면 우리가 만든 예외가 아니라 스프링에서 제공하는 예외에 적용하지 못함 => ResponseStatusException 이용
    public String responseStatusEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재 하지 않는 페이지를 요청 하셨습니다.", new IllegalArgumentException()); // (반환코드, 에러메세지, 예외)
    }

    @GetMapping("/api/default-handler-ex") // 파라미터 바인딩 에러는 클라이언트가 주어진 타입이 아닌 다른 타입의 값을 입력했으므로 클라이언트(400) 에러임 하지만 500으로 표시되게 되는 DefaultHandlerExceptionResolver를 이용하면 이를 해결할 수 있다. (저절로 적용됨 -> 400으로 표시됨)
    public String defaultHandlerException(@RequestParam Integer data) {
        return "ok";
    }

}
