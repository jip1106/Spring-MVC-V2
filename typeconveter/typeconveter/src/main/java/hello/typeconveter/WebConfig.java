package hello.typeconveter;

import hello.typeconveter.converter.IntegerToStringConverter;
import hello.typeconveter.converter.IpPortToStringConverter;
import hello.typeconveter.converter.StringToIntegerConverter;
import hello.typeconveter.converter.StringToIpPortConverter;
import hello.typeconveter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //주석처리 우선순위
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        //추가
        registry.addFormatter(new MyNumberFormatter());

    }

}
