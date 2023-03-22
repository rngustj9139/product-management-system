package koo.product.management.system.api.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

// @Component
public class TestException implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> { // 서블릿 방식의 예외 처리(오류 페이지)

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage1 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage2 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");

        factory.addErrorPages(errorPage1, errorPage2);
    }

}
