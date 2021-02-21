package softuni.exam_21_feb_2021.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam_21_feb_2021.constants.ArtistsConstants;
import softuni.exam_21_feb_2021.enumerations.ArtistEnum;
import softuni.exam_21_feb_2021.models.entity.Artist;
import softuni.exam_21_feb_2021.models.service.ArtistServiceModel;
import softuni.exam_21_feb_2021.repositories.ArtistRepository;
import softuni.exam_21_feb_2021.services.ArtistService;

import javax.annotation.PostConstruct;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
    }

    /* ------ Seed  ------ */
    @PostConstruct
    public void seedCategories() {
        if (artistRepository.count() == 0) {

            /* Seed Queen */
            Artist queen = new Artist(ArtistEnum.QUEEN, ArtistsConstants.QUEEN_CAREER_INFORMATION);
            this.artistRepository.saveAndFlush(queen);

            /* Seed Metallica */
            Artist metallica = new Artist(ArtistEnum.METALLICA, ArtistsConstants.METALLICA_CAREER_INFORMATION);
            this.artistRepository.saveAndFlush(metallica);

            /* Seed Madonna */
            Artist madonna = new Artist(ArtistEnum.MADONNA, ArtistsConstants.MADONNA_CAREER_INFORMATION);
            this.artistRepository.saveAndFlush(madonna);
        }
    }

    @Override
    public ArtistServiceModel findArtistByName(ArtistEnum name) {

        ArtistServiceModel artistServiceModel = this.artistRepository
                .findArtistByName(name)
                .map(a -> this.modelMapper.map(a, ArtistServiceModel.class))
                .orElse(null);

        return artistServiceModel;
    }
}
