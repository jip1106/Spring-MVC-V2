package hello.typeconveter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MyNumberFormatterTest {

    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    public void parse() throws Exception {
        Number result = formatter.parse("1,000", Locale.KOREA);

        assertThat(result).isEqualTo(1000L);    //Long Type
    }

    @Test
    public void print() throws Exception {
        String result = formatter.print(1000, Locale.KOREA);
        assertThat(result).isEqualTo("1,000");
    }
}