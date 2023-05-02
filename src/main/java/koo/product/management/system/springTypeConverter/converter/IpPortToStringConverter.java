package koo.product.management.system.springTypeConverter.converter;

import koo.product.management.system.springTypeConverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

/**
 *    StringToIntegerConverter converter = new StringToIntegerConverter();
 *    String source = "127.0.0.1:8080";
 *    Integer result = converter.convert(source);
 *    와 같이 컨버터를 직접 불러와 사용하는 것이 어렵기 때문에 개별 컨버전을 모아서 효과적으로 사용할 수 있게하는 ConversionService를 이용해야한다.
 *    ConversionServiceTest 확인하기
 *    WebConfig에도 컨버전 서비스를 이용해 컨버전을 등록 가능하다(확인하기)
 */
@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("convert source={}", source);

        return source.getIp() + ":" + source.getPort();
    }

}
