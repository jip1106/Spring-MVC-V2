package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSoruce;

    @Test
    void helloMessage(){
        //application.properties 파일에 basename로 messages를 지정 했으므로 messages.properties 파일에서 데이터 조회한다.
        //spring.messages.basename=messages,config.i18n.messages
        String result = messageSoruce.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode(){
       // messageSoruce.getMessage("no_code", null, null);

        assertThatThrownBy(() -> messageSoruce.getMessage("no_code", null , null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage(){

        String result = messageSoruce.getMessage("no_code", null, "default messages", null);
        assertThat(result).isEqualTo("default messages");

    }

    @Test
    void argumentMessage(){
        String message = messageSoruce.getMessage("hello.name" , new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    void twoArgumentMessage(){
        String message = messageSoruce.getMessage("hello.name.param" , new Object[]{"Spring","Basic"}, null);
        assertThat(message).isEqualTo("안녕 Spring Basic");
    }

    @Test
    void defaultLang(){
        assertThat(messageSoruce.getMessage("hello",null,null)).isEqualTo("안녕");
        assertThat(messageSoruce.getMessage("hello",null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang(){
        assertThat(messageSoruce.getMessage("hello",null,Locale.ENGLISH)).isEqualTo("hello");
    }
}
