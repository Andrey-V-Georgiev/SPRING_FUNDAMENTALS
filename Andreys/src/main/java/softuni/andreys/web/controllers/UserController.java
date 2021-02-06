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
import softuni.andreys.services.AuthService;
import softuni.andreys.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.andreys.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, AuthService authService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    /* Register */
    @GetMapping("/register")
    public String register(
            Model model,
            HttpSession httpSession
    ) {
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    /* Register confirm */
    @PostMapping("/register")
    public String registerConfirm(
            @Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        /* if errors */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userRegisterBindingModel", bindingResult);

            /* if confirmed password doesn't match */
            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("passwordMismatch", true);
            }
            return "redirect:/users/register";
        }
        /* if user already exists */
        if (this.userService.findUserByUsernameAndEmail(
                userRegisterBindingModel.getUsername(),
                userRegisterBindingModel.getEmail()) != null) {

            redirectAttributes.addFlashAttribute("userAlreadyExists", true);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        /* save to DB */
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return "redirect:/users/login";
    }

    /* Login */
    @GetMapping("/login")
    public String login(
            Model model,
            HttpSession httpSession
    ) {
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    /* Login confirm */
    @PostMapping("/login")
    public String loginConfirm(
            @Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
            BindingResult bindingResult,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ) {
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        /* if errors */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }

        /* find the user */
        UserServiceModel userServiceModel = this.userService.findByUsernameAndPassword(
                userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword()
        );

        /* if incorrect credentials */
        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("wrongCredentials", true);
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }

        /* set session */
        httpSession.setAttribute("userServiceModel", userServiceModel);
        return "redirect:/home";
    }

    /* Logout */
    @GetMapping("/logout")
    public String logout(
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        httpSession.invalidate();
        return "redirect:/";
    }
}
