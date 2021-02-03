package softuni.andreys.models.binding;

import org.hibernate.validator.constraints.Length;
import softuni.andreys.enums.CategoryEnums;
import softuni.andreys.enums.GenderEnums;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class ItemAddBindingModel {

    private String name;
    private String description;
    private BigDecimal price;
    private CategoryEnums category;
    private GenderEnums gender;

    public ItemAddBindingModel() {
    }

    public ItemAddBindingModel(String name, String description, BigDecimal price, CategoryEnums category, GenderEnums gender) {
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

    public CategoryEnums getCategory() {
        return category;
    }

    public void setCategory(CategoryEnums category) {
        this.category = category;
    }

    public GenderEnums getGender() {
        return gender;
    }

    public void setGender(GenderEnums gender) {
        this.gender = gender;
    }
}
