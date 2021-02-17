package softuni.shopping_list.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.shopping_list.enumerations.CategoryEnum;
import softuni.shopping_list.models.binding.ProductAddBindingModel;
import softuni.shopping_list.services.AuthService;
import softuni.shopping_list.services.ProductService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.shopping_list.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final AuthService authService;
    private final ProductService productService;

    @Autowired
    public ProductController(AuthService authService, ProductService productService) {
        this.authService = authService;
        this.productService = productService;
    }

    /* ------ Add product ------ */
    @GetMapping("/add")
    public String addProduct(
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        /* Attach the binding model */
        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }
        /* Attach additional fields */
        model.addAttribute("productAlreadyExists");
        model.addAttribute("allCategories", CategoryEnum.values());

        return "product-add";
    }

    /* ------ Add product confirm ------ */
    @PostMapping("/add")
    public String addProductConfirm(
            @Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        /* If errors */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "productAddBindingModel", bindingResult);
            return "redirect:/products/add";
        }
        /* If already exists */
        if (this.productService.findProductByName(productAddBindingModel.getName()) != null) {
            redirectAttributes.addFlashAttribute("productAlreadyExists", true);
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "productAddBindingModel", bindingResult);
            return "redirect:/products/add";
        }
        /* Save to DB */
        this.productService.addProduct(productAddBindingModel);
        return "redirect:/home";
    }

    /* ------Delete ------ */
    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable("id") String id,
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        this.productService.deleteItemById(id);
        return "redirect:/home";
    }

    /* ------ Delete all ------ */
    @GetMapping("/delete/all")
    public String deleteAllProducts(
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        this.productService.deleteAll();
        return "redirect:/home";
    }
}
