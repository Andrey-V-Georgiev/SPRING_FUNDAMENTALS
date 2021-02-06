package softuni.andreys.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.andreys.enums.CategoryEnums;
import softuni.andreys.enums.GenderEnums;
import softuni.andreys.models.binding.ItemAddBindingModel;
import softuni.andreys.models.view.ItemViewModel;
import softuni.andreys.services.AuthService;
import softuni.andreys.services.ItemService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.andreys.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final AuthService authService;

    @Autowired
    public ItemController(ItemService itemService, AuthService authService) {
        this.itemService = itemService;
        this.authService = authService;
    }

    /* Add item */
    @GetMapping("/add")
    public String addItem(
            Model model,
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        if (!model.containsAttribute("itemAddBindingModel")) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
        }
        model.addAttribute("allCategories", CategoryEnums.values());
        model.addAttribute("allGenders", GenderEnums.values());
        return "add-item";
    }

    /* Add item confirm */
    @PostMapping("/add")
    public String addItemConfirm(
            @Valid @ModelAttribute("itemAddBindingModel") ItemAddBindingModel itemAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        /* if errors */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "itemAddBindingModel", bindingResult);
            return "redirect:/items/add";
        }
        /* if item already exists */
        if (this.itemService.findItemByName(itemAddBindingModel.getName()) != null) {
            redirectAttributes.addFlashAttribute("itemAlreadyExists", true);
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "itemAddBindingModel", bindingResult);
            return "redirect:/items/add";
        }
        /* save to DB */
        this.itemService.addItem(itemAddBindingModel);
        return "redirect:/home";
    }

    /* Details */
    @GetMapping("/details/{id}")
    public String detailsItem(
            @PathVariable("id") String id,
            Model model,
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        ItemViewModel itemViewModel = this.itemService.findItemById(id);
        model.addAttribute("itemViewModel", itemViewModel);
        return "details-item";
    }

    /* Delete */
    @GetMapping("/delete/{id}")
    public String deleteItem(
            @PathVariable("id") String id,
            Model model,
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        this.itemService.deleteItemById(id);
        return "redirect:/home";
    }
}
