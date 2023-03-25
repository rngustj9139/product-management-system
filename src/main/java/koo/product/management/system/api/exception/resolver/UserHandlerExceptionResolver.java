package koo.product.management.system.api.exception.resolver;

import koo.product.management.system.api.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * HandlerExceptionResolver는 예외를 그냥 끝낼 수 있다 => WAS까지 예외가 올라가고 다시 오류페이지 컨트롤러를 호출하는 것이 아님
 */
@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof UserException) {
                log.info("UserException resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());

                if ("application/json".equals(acceptHeader)) {
                    HashMap<String, Object> errorResult = new HashMap<>();
                }
            }
        } catch (IOException e) {
            log.error("resolver ex", e); // exception은 {} 사용 안해도 된다.
        }

        return null;
    }

}
