package koo.product.management.system.springTypeConverter.converter;

import koo.product.management.system.springTypeConverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");

        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);

        Assertions.assertThat(result).isEqualTo("10");
    }

    @Test
    void IpPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);

        Assertions.assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void StringToIpPort() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        String source = "127.0.0.1:8080";
        Integer result = converter.convert(source);

        Assertions.assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080)); // @EqualsAndHashCode로 인해 isEqualTo 사용 가능
    }

}
