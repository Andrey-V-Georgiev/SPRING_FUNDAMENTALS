package softuni.exam_21_feb_2021.services;

import softuni.exam_21_feb_2021.models.binding.AlbumAddBindingModel;
import softuni.exam_21_feb_2021.models.service.AlbumServiceModel;
import softuni.exam_21_feb_2021.models.view.AlbumViewModel;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AlbumService {

    AlbumServiceModel findAlbumByName(String name);

    AlbumServiceModel addAlbum(AlbumAddBindingModel albumAddBindingModel, HttpSession httpSession);

    List<AlbumViewModel> findAllSortedByCopies();

    Integer findTotalCopies();

    void deleteAlbumById(String id);
}
