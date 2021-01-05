package softuni.car_shop.models.entities;

import softuni.car_shop.enums.UserRolesEnum;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {

    private UserRolesEnum role;

    public UserRole() {
    }

    public UserRole(UserRolesEnum role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    public UserRolesEnum getRole() {
        return role;
    }

    public void setRole(UserRolesEnum role) {
        this.role = role;
    }
}
