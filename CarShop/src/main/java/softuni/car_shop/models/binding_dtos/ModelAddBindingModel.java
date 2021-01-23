package softuni.car_shop.models.binding_dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ModelAddBindingModel {

    private String name;
    private Integer startYear;
    private Integer endYear;
    private String imageUrl;
    private String brand;

    public ModelAddBindingModel() {
    }

    public ModelAddBindingModel(String name, Integer startYear, Integer endYear, String imageUrl, String brand) {
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
        this.imageUrl = imageUrl;
        this.brand = brand;
    }

    @NotNull
    @Length(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Min(1950)
    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    @NotNull
    @Min(1950)
    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    @NotNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
