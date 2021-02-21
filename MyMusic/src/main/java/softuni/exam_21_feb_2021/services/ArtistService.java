package softuni.exam_21_feb_2021.services;

import softuni.exam_21_feb_2021.enumerations.ArtistEnum;
import softuni.exam_21_feb_2021.models.service.ArtistServiceModel;

public interface ArtistService {
    ArtistServiceModel findArtistByName(ArtistEnum name);
}
