package koo.product.management.system.api.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import koo.product.management.system.api.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


// * HandlerExceptionResolver는 예외를 그냥 끝낼 수 있다 => WAS까지 예외가 올라가고 다시 오류페이지 컨트롤러를 호출하는 것이 아님 (응답만 하고 WAS에는 정상 응답이 가게 된다.)
// * but ExceptionResolver를 직접 구현 하자니 상당히 복잡함, => 스프링이 제공하는 ExceptionResolver를 이용하기
// * 1. ExceptionHandlerExceptionResolver => @ExceptionHandler 처리
// * 2. ResponseStatusExceptionResolver => @ResponseStatus(value = HttpStatus.NOT_FOUND) 처리
// * 3. DefaultHandlerExceptionResolver => 스프링 내부 기본 예외를 처리
@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper(); // 객체 => json 변환을 위해 ObjectMapper 사용

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof UserException) {
                log.info("UserException resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());

                if ("application/json".equals(acceptHeader)) {
                    HashMap<String, Object> errorResult = new HashMap<>();
                    errorResult.put("ex", ex.getClass());
                    errorResult.put("message", ex.getMessage());

                    String result = objectMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result);

                    return new ModelAndView(); // 응답만 하고 예외를 그냥 끝냄
                } else { // accept header가 html인 경우
                    return new ModelAndView("error/500");
                }
            }
        } catch (IOException e) {
            log.error("resolver ex", e); // exception은 {} 사용 안해도 된다.
        }

        return null;
    }

}
