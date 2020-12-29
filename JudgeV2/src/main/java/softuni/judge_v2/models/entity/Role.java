package softuni.judge_v2.models.entity;

import softuni.judge_v2.enums.Roles;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private Roles name;

    public Role() {
    }

    public Role(Roles name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }
}
