package softuni.exam_21_feb_2021.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.exam_21_feb_2021.services.AlbumService;
import softuni.exam_21_feb_2021.services.AuthService;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    private final AuthService authService;
    private final AlbumService albumService;

    @Autowired
    public HomeController(AuthService authService, AlbumService albumService) {
        this.authService = authService;
        this.albumService = albumService;
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
        model.addAttribute("albums", this.albumService.findAllSortedByCopies());
        model.addAttribute("totalCopies", this.albumService.findTotalCopies());

        return "home";
    }
}
