package softuni.andreys.models.entity;

import org.hibernate.validator.constraints.Length;
import softuni.andreys.enums.GenderEnums;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private GenderEnums gender;

    public Item() {
    }

    public Item(String name, String description, BigDecimal price, Category category, GenderEnums gender) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.gender = gender;
    }

    @Length(min = 2, message = "Name must be at least 2 symbols")
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3, message = "Description must be at least 3 symbols")
    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0", message = "Price must be positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Enumerated(EnumType.STRING)
    public GenderEnums getGender() {
        return gender;
    }

    public void setGender(GenderEnums gender) {
        this.gender = gender;
    }
}
