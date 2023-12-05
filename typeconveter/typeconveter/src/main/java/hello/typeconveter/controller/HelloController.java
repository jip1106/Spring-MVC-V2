package hello.typeconveter.controller;

import hello.typeconveter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request){
        String data = request.getParameter("data");       //문자 타입으로 조회
        Integer intValue = Integer.valueOf(data);               //숫자 타입으로 변경

        System.out.println("intValue = " + intValue);

        return "ok";
    }

    //http 쿼리 스트링으로 전ㄷ라하는 data=10 은 문자 10이다.
    //스프링이 중간에서 타입을 Integer으로 변환 해줘서 Integer 타입으로 받을 수 있다.
    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data){
        System.out.println("data = " + data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort){
        System.out.println("ipPort.getIp() = " + ipPort.getIp());
        System.out.println("ipPort.getPort() = " + ipPort.getPort());
        return "ok";
    }

    @GetMapping("/ip-port-modelattribute")
    public String ipPortModelAttribute(IpPort ipPort){
        System.out.println("ipPort.getIp() = " + ipPort.getIp());
        System.out.println("ipPort.getPort() = " + ipPort.getPort());
        return "ok";
    }
}
