package softuni.car_shop.models.binding_dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OfferAddBindingModel {

    private String model;
    private Integer year;
    private String engine;
    private Integer mileage;
    private String category;
    private BigDecimal price;
    private String transmission;
    private String imageUrl;
    private String username;
    private String description;

    public OfferAddBindingModel() {
    }

    public OfferAddBindingModel(String model, Integer year, String engine, Integer mileage, String category, BigDecimal price, String transmission, String imageUrl, String username, String description) {
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.mileage = mileage;
        this.category = category;
        this.price = price;
        this.transmission = transmission;
        this.imageUrl = imageUrl;
        this.username = username;
        this.description = description;
    }

    @NotNull
    @Length(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull
    @Min(1950)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @NotNull
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @NotNull
    @Min(0)
    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @NotNull
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NotNull
    @DecimalMin("0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @NotNull
    @Length(min = 5, message = "Length must be at least 5 characters")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull
    @Length(min = 3, max = 20, message = "Username must be between 3 and 20 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Length(min = 10, message = "Description must be between at least 10 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
