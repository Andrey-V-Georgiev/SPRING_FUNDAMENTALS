package softuni.judge_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.judge_v2.models.entity.Homework;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, String> {

    @Query(value = "SELECT h FROM Homework h ORDER BY h.comments.size ASC" )
    List<Homework> findHomeworkByLowestComments();
}
