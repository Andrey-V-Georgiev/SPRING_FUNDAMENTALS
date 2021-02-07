package softuni.shopping_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.shopping_list.models.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndEmail(String username, String email);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
