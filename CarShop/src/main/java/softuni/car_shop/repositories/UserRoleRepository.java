package softuni.car_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.car_shop.enums.UserRolesEnum;
import softuni.car_shop.models.entities.UserRole;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

   Optional<UserRole> findUserRoleByRole(UserRolesEnum role);
}
