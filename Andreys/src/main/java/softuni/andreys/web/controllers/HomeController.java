package softuni.andreys.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    /* Index */
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    /* Home */
    @GetMapping("/home")
    public String home(Model model, HttpSession httpSession) {
        return "home";
    }
}
