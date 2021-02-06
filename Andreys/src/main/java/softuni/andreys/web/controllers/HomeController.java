package softuni.andreys.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.andreys.services.ItemService;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    private final ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    /* Index */
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    /* Home */
    @GetMapping("/home")
    public String home(Model model, HttpSession httpSession) {
        model.addAttribute("allItems",  this.itemService.findAll());
        return "home";
    }
}
