package koo.product.management.system;

import koo.product.management.system.api.exception.resolver.MyHandlerExceptionResolver;
import koo.product.management.system.api.exception.resolver.UserHandlerExceptionResolver;
import koo.product.management.system.interceptor.LogInterceptor;
import koo.product.management.system.interceptor.LoginCheckInterceptor;
import koo.product.management.system.springTypeConverter.converter.IntegerToStringConverter;
import koo.product.management.system.springTypeConverter.converter.IpPortToStringConverter;
import koo.product.management.system.springTypeConverter.converter.StringToIntegerConverter;
import koo.product.management.system.springTypeConverter.converter.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override // 인터셉터 등록
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**", "/*.ico", "/error");
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) { // api/exception/resolver/MyHandlerExceptionResolver를 위해 등록
        resolvers.add(new MyHandlerExceptionResolver());
        resolvers.add(new UserHandlerExceptionResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) { // 컨버터 등록 (스프링은 내부에서 ConversionService 제공한다.) (HelloController에서 직접 사용해보기)
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
    }

}
