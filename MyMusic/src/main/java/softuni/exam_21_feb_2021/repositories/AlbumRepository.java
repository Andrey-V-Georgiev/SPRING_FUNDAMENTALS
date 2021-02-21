package softuni.exam_21_feb_2021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam_21_feb_2021.models.entity.Album;
import softuni.exam_21_feb_2021.models.view.AlbumViewModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository  extends JpaRepository<Album, String> {

    Optional<Album> findAlbumByName(String name);

    @Query(value = "SELECT a FROM Album a ORDER BY a.copies DESC")
    List<Album> findAllSortedByCopies();

    @Query(value = "SELECT SUM(a.copies) FROM Album a")
    Integer findTotalCopies();
}
