package koo.product.management.system.SpringTypeConverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class SrtingToIntegerConverter implements Converter<String, Integer> { // converter 라이브러리는 많으니 org.springframework.core.convert.converter.Converter 이용하기

    @Override
    public Integer convert(String source) { // String을 Integer로 변경
        log.info("convert source={}", source);

        return Integer.valueOf(source);
    }

}
