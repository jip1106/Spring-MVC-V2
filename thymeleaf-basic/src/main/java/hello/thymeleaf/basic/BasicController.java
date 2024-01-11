package hello.thymeleaf.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring! <b>태그사용</b>");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "Hello <b>Spring <br> </b>");
        StringBuilder sb = new StringBuilder();
        sb.append("Hello <b>Spring</b> <br>");
        sb.append("th:text , th:utext 테스트 <br>");

        String str = sb.toString();

        model.addAttribute("str", str);

        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model){

        User userA = new User("userA",10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();

        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);


        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) {

        session.setAttribute("sessionData", "Hello Session");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello " + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1","data1");
        model.addAttribute("param2","data2");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);

        //map test
        createMap(model);
        createMapList(model);
        return "basic/each";
    }

    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    private void addUsers2(Model model){
        List<User> list = new ArrayList<>();
        list.add(new User(1L,"userA", 10));
        list.add(new User(2L,"userB", 20));
        list.add(new User(3L,"userC", 30));
        model.addAttribute("users", list);
    }

    private void createMap(Model model){
        Map<Long, List<User>> map = new HashMap<>();
        addUsers2(model);
        List<User> users = (List<User>)model.getAttribute("users");

        for (User user : users) {
            List<User> list = new ArrayList<>();
            list.add(user);

            map.put(user.getId(),list);
        }

        model.addAttribute("mapData", map);
    }

    private void createMapList(Model model){
        Map<Long, List<User>> map = new HashMap<>();
        addUsers2(model);

        List<User> users = (List<User>)model.getAttribute("users");

        for (User user : users) {
            List<User> list = new ArrayList<>();
            list.add(user);

            map.put(user.getId(),list);
        }

        model.addAttribute("mapListData", map);
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("UserA", 10));
        addUsers(model);
        return "basic/javascript";
    }



    @Data
    static class User{
        private Long id;
        private String username;
        private int age;

        public User(String username, int age){
            this.username = username;
            this.age = age;
        }
        public User(Long id, String username, int age){
            this(username, age);
            this.id = id;
        }
    }
}
