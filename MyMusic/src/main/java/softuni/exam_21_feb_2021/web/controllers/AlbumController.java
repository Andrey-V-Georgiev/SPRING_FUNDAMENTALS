package softuni.exam_21_feb_2021.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.exam_21_feb_2021.enumerations.ArtistEnum;
import softuni.exam_21_feb_2021.enumerations.GenreEnum;
import softuni.exam_21_feb_2021.models.binding.AlbumAddBindingModel;
import softuni.exam_21_feb_2021.services.AlbumService;
import softuni.exam_21_feb_2021.services.AuthService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.exam_21_feb_2021.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AuthService authService;
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AuthService authService, AlbumService albumService) {
        this.authService = authService;
        this.albumService = albumService;
    }

    /* ------ Add album ------ */
    @GetMapping("/add")
    public String addAlbum(
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        /* Attach the binding model */
        if (!model.containsAttribute("albumAddBindingModel")) {
            model.addAttribute("albumAddBindingModel", new AlbumAddBindingModel());
        }
        /* Attach additional fields */
        model.addAttribute("albumAlreadyExists");
        model.addAttribute("allArtists", ArtistEnum.values());
        model.addAttribute("allGenres", GenreEnum.values());

        return "add-album";
    }

    /* ------ Add album confirm ------ */
    @PostMapping("/add")
    public String addAlbumConfirm(
            @Valid @ModelAttribute("albumAddBindingModel") AlbumAddBindingModel albumAddBindingModel,
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
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "albumAddBindingModel", bindingResult);
            return "redirect:/albums/add";
        }

        /* If already exists */
        if (this.albumService.findAlbumByName(albumAddBindingModel.getName()) != null) {
            redirectAttributes.addFlashAttribute("productAlreadyExists", true);
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "albumAddBindingModel", bindingResult);
            return "redirect:/albums/add";
        }
        /* Save to DB */
        this.albumService.addAlbum(albumAddBindingModel, httpSession);
        return "redirect:/home";
    }

    /* ------Delete ------ */
    @GetMapping("/delete/{id}")
    public String deleteAlbum(
            @PathVariable("id") String id,
            Model model,
            HttpSession httpSession
    ) {
        /* Validate authorization */
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        this.albumService.deleteAlbumById(id);
        return "redirect:/home";
    }
}
