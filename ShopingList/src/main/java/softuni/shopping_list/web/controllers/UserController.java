package softuni.shopping_list.web.controllers;

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
import softuni.shopping_list.models.binding.UserLoginBindingModel;
import softuni.shopping_list.models.binding.UserRegisterBindingModel;
import softuni.shopping_list.models.service.UserServiceModel;
import softuni.shopping_list.services.AuthService;
import softuni.shopping_list.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.shopping_list.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/users")
public class UserController {

    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, AuthService authService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    /* ------ Register ------ */
    @GetMapping("/register")
    public String register(
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        /* Attach the binding model */
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        /* Attach additional fields */
        model.addAttribute("userAlreadyExists");
        model.addAttribute("passwordMismatch");
        return "register";
    }

    /* ------ Register confirm ------ */
    @PostMapping("/register")
    public String registerConfirm(
            @Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        /* If errors */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        /* If confirmed password doesn't match */
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordMismatch", true);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        /* Find by username and email */
        UserServiceModel userByUsernameAndEmail = this.userService.findUserByUsernameAndEmail(
                userRegisterBindingModel.getUsername(),
                userRegisterBindingModel.getEmail()
        );
        /* If user already exists */
        if (userByUsernameAndEmail != null) {
            redirectAttributes.addFlashAttribute("userAlreadyExists", true);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        /* Save to DB */
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return "redirect:/users/login";
    }

    /* ------ Login ------ */
    @GetMapping("/login")
    public String login(
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        /* Attach the binding model */
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        /* Attach additional fields */
        model.addAttribute("wrongCredentials");
        return "login";
    }

    /* ------ Login confirm ------ */
    @PostMapping("/login")
    public String loginConfirm(
            @Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
            BindingResult bindingResult,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ) {
        /* Validate authorization */
        if (this.authService.haveSession(httpSession)) {
            return "redirect:/home";
        }
        /* If errors */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }

        /* Find by username and password */
        UserServiceModel userServiceModel = this.userService.findByUsernameAndPassword(
                userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword()
        );

        /* If incorrect credentials */
        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("wrongCredentials", true);
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }

        /* Set session */
        httpSession.setAttribute("userServiceModel", userServiceModel);
        return "redirect:/home";
    }

    /* ------ Logout ------ */
    @GetMapping("/logout")
    public String logout(
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        httpSession.invalidate();
        return "redirect:/";
    }
}
