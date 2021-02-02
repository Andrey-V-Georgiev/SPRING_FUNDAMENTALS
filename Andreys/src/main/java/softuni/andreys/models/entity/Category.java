package softuni.andreys.models.entity;

import softuni.andreys.enums.CategoryEnums;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryEnums name;
    private String description;

    public Category() {
    }

    public Category(CategoryEnums name) {
        this.name = name;
    }

    public Category(CategoryEnums name, String description) {
        this.name = name;
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    public CategoryEnums getName() {
        return name;
    }

    public void setName(CategoryEnums name) {
        this.name = name;
    }

    @Column(name = "description", columnDefinition="TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
