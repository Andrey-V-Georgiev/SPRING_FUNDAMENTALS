package softuni.judge_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.judge_v2.models.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {

}
