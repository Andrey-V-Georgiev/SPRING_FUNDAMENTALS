package softuni.car_shop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.car_shop.services.AuthService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final AuthService authService;

    @Autowired
    public HomeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession httpSession) {
        if(!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        return "all";
    }
}
