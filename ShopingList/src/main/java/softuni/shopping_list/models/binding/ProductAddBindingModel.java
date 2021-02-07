package softuni.shopping_list.models.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import softuni.shopping_list.enumerations.CategoryEnum;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddBindingModel {

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime neededBefore;
    private CategoryEnum category;

    public ProductAddBindingModel() {
    }

    public ProductAddBindingModel(String name, String description, BigDecimal price, LocalDateTime neededBefore, CategoryEnum category) {
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

    @DecimalMin(value = "0", message = "Price must be positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @FutureOrPresent(message = "Date cannot be in the past")
    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    @NotNull
    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
