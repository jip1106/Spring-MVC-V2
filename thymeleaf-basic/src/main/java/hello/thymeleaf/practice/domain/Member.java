package hello.thymeleaf.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class Member {

    private Long id;
    private String name;
    private Point point;
    private int age;

    public Member(Long id, String name, Point point, int age){
        this.id = id;
        this.name = name;
        this.point = point;
        this.age = age;
    }

}
