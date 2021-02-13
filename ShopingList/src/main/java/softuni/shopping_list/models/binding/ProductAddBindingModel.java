package softuni.shopping_list.models.binding;

import org.hibernate.validator.constraints.Length;
import softuni.shopping_list.enumerations.CategoryEnum;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductAddBindingModel {

    private String name;
    private String description;
    private BigDecimal price;
    private String neededBefore;
    private CategoryEnum category;

    public ProductAddBindingModel() {
    }

    public ProductAddBindingModel(String name, String description, BigDecimal price, String neededBefore, CategoryEnum category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.neededBefore = neededBefore;
        this.category = category;
    }

    @Length(min = 3, max = 20, message = "Name must be between 3 an 20 symbols")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 5, message = "Description must be at least 5 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Must enter price")
    @DecimalMin(value = "0", message = "Price must be positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Length(min = 16, message = "Please enter date and time in valid format")
    public String getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(String neededBefore) {
        this.neededBefore = neededBefore;
    }

    @NotNull(message = "Must enter category")
    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
