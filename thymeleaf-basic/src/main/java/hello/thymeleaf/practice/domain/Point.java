package hello.thymeleaf.practice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
    private Long money;

    public Point(long money) {
        this.money = money;
    }
}
