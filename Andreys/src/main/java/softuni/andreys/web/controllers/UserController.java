package softuni.andreys.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.andreys.models.binding.UserLoginBindingModel;
import softuni.andreys.models.binding.UserRegisterBindingModel;
import softuni.andreys.models.service.UserServiceModel;
import softuni.andreys.services.UserService;

import javax.validation.Valid;

import static softuni.andreys.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /* Register */
    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(
            @Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        /* IF ERRORS */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userRegisterBindingModel", bindingResult);

            /* IF CONFIRMED_PASSWORD MISMATCH */
            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("passwordMismatch", true);
            }
            return "redirect:/users/register";
        }
        /* IF USER ALREADY EXISTS */
        if (this.userService.findUserByUsernameAndEmail(
                userRegisterBindingModel.getUsername(),
                userRegisterBindingModel.getEmail()) != null) {

            redirectAttributes.addFlashAttribute("userAlreadyExists", true);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        /* SAVE TO DB */
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return "redirect:/users/login";
    }

    /* Login */
    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

}