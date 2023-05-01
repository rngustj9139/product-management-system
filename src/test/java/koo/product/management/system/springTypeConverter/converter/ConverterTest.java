package koo.product.management.system.springTypeConverter.converter;

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

}
