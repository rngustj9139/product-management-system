package koo.product.management.system.springTypeConverter.converter;

import koo.product.management.system.springTypeConverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {

    @Override
    public IpPort convert(String source) { // source는 "127.0.0.1:8080"
        log.info("convert source={}", source);

        String[] split = source.split(":");
        String ip = split[0];
        Integer port = Integer.parseInt(split[1]);

        return new IpPort(ip, port);
    }

}
