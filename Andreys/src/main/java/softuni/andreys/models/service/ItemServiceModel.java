package softuni.andreys.models.service;

import org.hibernate.validator.constraints.Length;
import softuni.andreys.enums.GenderEnums;
import softuni.andreys.models.entity.Category;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class ItemServiceModel extends BaseServiceModel{

    private String name;
    private String description;
    private BigDecimal price;
    private CategoryServiceModel category;
    private GenderEnums gender;

    public ItemServiceModel() {
    }

    public ItemServiceModel(String name, String description, BigDecimal price, CategoryServiceModel category, GenderEnums gender) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.gender = gender;
    }

    @Length(min = 2, message = "Name must be at least 2 symbols")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3, message = "Description must be at least 3 symbols")
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

    public CategoryServiceModel getCategory() {
        return category;
    }

    public void setCategory(CategoryServiceModel category) {
        this.category = category;
    }

    public GenderEnums getGender() {
        return gender;
    }

    public void setGender(GenderEnums gender) {
        this.gender = gender;
    }
}
