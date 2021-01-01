package softuni.judge_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.judge_v2.models.entity.Homework;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, String> {

}
