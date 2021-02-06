package softuni.andreys.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.andreys.services.AuthService;
import softuni.andreys.services.ItemService;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    private final ItemService itemService;
    private final AuthService authService;

    @Autowired
    public HomeController(ItemService itemService, AuthService authService) {
        this.itemService = itemService;
        this.authService = authService;
    }

    /* Index */
    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
        if(this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        return "index";
    }

    /* Home */
    @GetMapping("/home")
    public String home(Model model, HttpSession httpSession) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        model.addAttribute("allItems",  this.itemService.findAll());
        return "home";
    }
}
