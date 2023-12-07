package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;
    //@GetMapping("/")
    public String home() {
        return "home";
    }

    //@CookieValue를 사용하면 편하게 쿠키를 조회할 수 있음
    // 로그인 하지 않은 사용자도 홈에 접근할 수 있기 때문에 required = false를 사용
    @GetMapping("/V1")
    public String homeLoginV1(@CookieValue(name="memberId", required = false) Long memberId, Model model){
        if(memberId == null){
            return "home";
        }

        //로그인
        Member loginMember = memberRepository.findById(memberId);

        if(loginMember == null){
            return "home";
        }


        model.addAttribute("member",loginMember);

        return "loginHome";

    }

    @GetMapping("/V2")
    public String homeLoginV2(HttpServletRequest request, Model model){

        //세션 관리자에 저장된 회원정보 조회
        Member member = (Member)sessionManager.getSession(request);

        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);

        return "loginHome";
    }

    @GetMapping("/V3")
    public String homeLoginV3(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);

        if(session == null){
            return "home";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);

        return "loginHome";
    }


    //스프링은 세션을 더 편리하게 사용할 수 있도록 @SessionAttribute 를 지원
    @GetMapping("/V3Spring")
    public String homeLoginV3Spring(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);

        return "loginHome";
    }


    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model){

        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);

        return "loginHome";
    }
}