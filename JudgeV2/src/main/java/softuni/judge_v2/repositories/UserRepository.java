package softuni.judge_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.judge_v2.models.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
