package koo.product.management.system.api.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HandlerExceptionResolver란
 * 서버 내부에서 예외가 발생하면 api 응답에서는 무조건 500으로 받게 됨
 * 예외가 발생해도 500이 아닌 400, 404등으로 반환하고 싶고, API마다 예외발생시 형식등을 다르게 하고 싶을때 사용
 * preHandle => handler adapter => handler => HandlerExceptionResolver => (예외 핸들링) => after completion => 정상응답이 WAS로 나감
 * (WebConfig에 등록해야함)
 */
@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());

                return new ModelAndView(); // 예외가 WAS 까지 않가고 정상 응답이 간다.
            }
        } catch (IOException e) {
                log.error("resolver ex", e);
        }

        return null; // null을 리턴하게 되면 예외가 WAS까지 간다.
    }

}
