package hello.typeconveter.converter;

import hello.typeconveter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class ConverterTest {

    @Test
    public void stringToInteger() throws Exception {
        //given
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");

        assertThat(result).isEqualTo(10);
    }

    @Test
    public void IntegerToString() throws Exception {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);

        assertThat(result).isEqualTo("10");
    }

    @Test
    public void stringToIpPort() throws Exception {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);

        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    public void ipPortToString() throws Exception {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);

        assertThat(result).isEqualTo(new IpPort("127.0.0.1",8080));

    }
}
