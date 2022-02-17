package pl.szymonstankowski.portfolioLabDriverApp.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.szymonstankowski.portfolioLabDriverApp.user.User;
import pl.szymonstankowski.portfolioLabDriverApp.user.UserService;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }
    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userService.saveUser(user);
        return "-created-";
    }
}
