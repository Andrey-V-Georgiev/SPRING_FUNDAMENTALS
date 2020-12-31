package softuni.judge_v2.models.entity;


import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = false,unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
