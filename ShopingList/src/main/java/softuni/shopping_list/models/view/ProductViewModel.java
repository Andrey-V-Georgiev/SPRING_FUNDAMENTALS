package softuni.shopping_list.models.view;

import softuni.shopping_list.models.service.CategoryServiceModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductViewModel {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime neededBefore;
    private CategoryServiceModel category;

    public ProductViewModel() {
    }

    public ProductViewModel(String id, String name, String description, BigDecimal price, LocalDateTime neededBefore, CategoryServiceModel category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.neededBefore = neededBefore;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public CategoryServiceModel getCategory() {
        return category;
    }

    public void setCategory(CategoryServiceModel category) {
        this.category = category;
    }
}
