package softuni.andreys.models.view;

import softuni.andreys.enums.GenderEnums;
import softuni.andreys.models.service.CategoryServiceModel;

import java.math.BigDecimal;

public class ItemViewModel {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryServiceModel category;
    private GenderEnums gender;
    private String imageUrl;

    public ItemViewModel() {
    }

    public ItemViewModel(String id, String name, String description, BigDecimal price, CategoryServiceModel category, GenderEnums gender, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.gender = gender;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
