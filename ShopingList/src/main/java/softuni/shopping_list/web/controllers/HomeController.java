package softuni.shopping_list.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.shopping_list.services.AuthService;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    private final AuthService authService;

    @Autowired
    public HomeController( AuthService authService) {
        this.authService = authService;
    }

    /* Index */
    @GetMapping("/")
    public String index(
            Model model,
            HttpSession httpSession
    ) {
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        return "index";
    }

    /* Home */
    @GetMapping("/home")
    public String home(
            Model model,
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        //model.addAttribute("allItems", this.itemService.findAll());
        return "home";
    }
}
