package hello.thymeleaf.basic;

import hello.thymeleaf.practice.domain.Member;
import hello.thymeleaf.practice.domain.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practice")
public class PracticeController {

    @GetMapping("/main")
    public String goMain(Model model){

        //header 정보 세팅
        callHeaderData(model);

        return "practice/layout/main";

    }

    @GetMapping("/header")
    public String header(Model model){

        Member member = new Member(1L, "jun", new Point(20000L), 30);

        model.addAttribute("member",member);
        return "practice/common/header";
    }

    private void callHeaderData(Model model){
        Member member = new Member(1L, "jun", new Point(20000L), 30);

        model.addAttribute("member",member);
    }
}
