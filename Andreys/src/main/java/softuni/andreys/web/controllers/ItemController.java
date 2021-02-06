package softuni.andreys.web.controllers;

import org.modelmapper.ModelMapper;
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
import softuni.andreys.services.CategoryService;
import softuni.andreys.services.ItemService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.andreys.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ModelMapper modelMapper;
    private final ItemService itemService;
    private final CategoryService categoryService;
    private final AuthService authService;

    @Autowired
    public ItemController(ModelMapper modelMapper, ItemService itemService, CategoryService categoryService, AuthService authService) {
        this.modelMapper = modelMapper;
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.authService = authService;
    }

    /* Add */
    @GetMapping("/add")
    public String addItem(Model model, HttpSession httpSession) {
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
        /* IF ERRORS */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "itemAddBindingModel", bindingResult);
            return "redirect:/items/add";
        }
        /* IF ITEM ALREADY EXISTS */
        if (this.itemService.findItemByName(itemAddBindingModel.getName()) != null) {
            redirectAttributes.addFlashAttribute("itemAlreadyExists", true);
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "itemAddBindingModel", bindingResult);
            return "redirect:/items/add";
        }
        /* SAVE TO DB */
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
