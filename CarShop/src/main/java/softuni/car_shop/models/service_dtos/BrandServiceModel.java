package softuni.car_shop.models.service_dtos;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class BrandServiceModel extends BaseServiceModel  {

    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BrandServiceModel() {
    }

    public BrandServiceModel(String name, LocalDateTime created, LocalDateTime modified) {
        this.name = name;
        this.created = created;
        this.modified = modified;
    }

    @Length(min = 2, message = "Name must be at least 2 symbols")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
