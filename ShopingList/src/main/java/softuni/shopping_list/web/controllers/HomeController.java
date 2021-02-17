package softuni.shopping_list.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.shopping_list.enumerations.CategoryEnum;
import softuni.shopping_list.services.AuthService;
import softuni.shopping_list.services.ProductService;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    private final AuthService authService;
    private final ProductService productService;

    @Autowired
    public HomeController(AuthService authService, ProductService productService) {
        this.authService = authService;
        this.productService = productService;
    }

    /* ------ Index ------ */
    @GetMapping("/")
    public String index(
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        return "index";
    }

    /* ------ Home ------ */
    @GetMapping("/home")
    public String home(
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        /* Attach additional fields */
        model.addAttribute("foods", this.productService.findProductsByCategory(CategoryEnum.FOOD));
        model.addAttribute("drinks", this.productService.findProductsByCategory(CategoryEnum.DRINK));
        model.addAttribute("households", this.productService.findProductsByCategory(CategoryEnum.HOUSEHOLD));
        model.addAttribute("others", this.productService.findProductsByCategory(CategoryEnum.OTHER));
        model.addAttribute("totalPrice", this.productService.findTotalPrice());

        return "home";
    }
}
