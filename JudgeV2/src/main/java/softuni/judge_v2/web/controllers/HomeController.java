package softuni.judge_v2.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.judge_v2.services.AuthService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession httpSession) {
        if(!this.authService.haveSession(httpSession)) {
           return "redirect:/";
        }
        return "home";
    }
}
