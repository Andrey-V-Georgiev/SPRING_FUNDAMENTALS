package softuni.shopping_list.models.entity;

import softuni.shopping_list.enumerations.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryEnum name;
    private String description;

    public Category() {
    }

    public Category(CategoryEnum name) {
        this.name = name;
    }

    public Category(CategoryEnum name, String description) {
        this.name = name;
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
