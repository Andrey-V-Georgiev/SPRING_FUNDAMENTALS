package softuni.exam_21_feb_2021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam_21_feb_2021.enumerations.ArtistEnum;
import softuni.exam_21_feb_2021.models.entity.Artist;

import java.util.Optional;

@Repository
public interface ArtistRepository  extends JpaRepository<Artist, String> {

    Optional<Artist> findArtistByName(ArtistEnum name);
}
