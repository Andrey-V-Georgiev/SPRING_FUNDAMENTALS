package softuni.exam_21_feb_2021.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam_21_feb_2021.models.binding.AlbumAddBindingModel;
import softuni.exam_21_feb_2021.models.entity.Album;
import softuni.exam_21_feb_2021.models.service.AlbumServiceModel;
import softuni.exam_21_feb_2021.models.service.ArtistServiceModel;
import softuni.exam_21_feb_2021.models.service.UserServiceModel;
import softuni.exam_21_feb_2021.models.view.AlbumViewModel;
import softuni.exam_21_feb_2021.repositories.AlbumRepository;
import softuni.exam_21_feb_2021.services.AlbumService;
import softuni.exam_21_feb_2021.services.ArtistService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final ModelMapper modelMapper;
    private final AlbumRepository albumRepository;
    private final ArtistService artistService;

    @Autowired
    public AlbumServiceImpl(ModelMapper modelMapper, AlbumRepository albumRepository, ArtistService artistService) {
        this.modelMapper = modelMapper;
        this.albumRepository = albumRepository;
        this.artistService = artistService;
    }

    /* ------ Find by name ------ */
    @Override
    public AlbumServiceModel findAlbumByName(String name) {
        System.out.println();
        AlbumServiceModel albumServiceModel = this.albumRepository
                .findAlbumByName(name)
                .map(p -> this.modelMapper.map(p, AlbumServiceModel.class))
                .orElse(null);

        return albumServiceModel;
    }

    /* ------ Add album ------ */
    @Override
    public AlbumServiceModel addAlbum(AlbumAddBindingModel albumAddBindingModel, HttpSession httpSession) {

        AlbumServiceModel albumServiceModel = this.modelMapper.map(
                albumAddBindingModel,
                AlbumServiceModel.class
        );
        /* Set Artist */
        ArtistServiceModel artistByName = this.artistService.findArtistByName(albumAddBindingModel.getArtist());
        albumServiceModel.setArtist(artistByName);

        /* Set AddedFrom*/
        albumServiceModel.setAddedFrom((UserServiceModel) httpSession.getAttribute("userServiceModel"));

        /* Save to DB */
        Album albumSaved = this.albumRepository.saveAndFlush(
                this.modelMapper.map(albumServiceModel, Album.class)
        );
        return this.modelMapper.map(albumSaved, AlbumServiceModel.class);
    }

    @Override
    public List<AlbumViewModel> findAllSortedByCopies() {

        List<AlbumViewModel> albumViewModels = this.albumRepository
                .findAllSortedByCopies()
                .stream()
                .map(a -> this.modelMapper.map(a, AlbumViewModel.class))
                .collect(Collectors.toList());

        return albumViewModels;
    }

    @Override
    public Integer findTotalCopies() {
        Integer totalCopies = this.albumRepository.findTotalCopies();
        return totalCopies;
    }

    /* ------ Delete ------ */
    @Override
    public void deleteAlbumById(String id) {
        this.albumRepository.deleteById(id);
    }
}
