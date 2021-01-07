package softuni.car_shop.models.binding_dtos;

import org.hibernate.validator.constraints.Length;

public class BrandAddBindingModel{

    private String name;

    public BrandAddBindingModel() {
    }

    public BrandAddBindingModel(String name) {
        this.name = name;
    }

    @Length(min = 2, message = "Name must be at least 2 symbols")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
