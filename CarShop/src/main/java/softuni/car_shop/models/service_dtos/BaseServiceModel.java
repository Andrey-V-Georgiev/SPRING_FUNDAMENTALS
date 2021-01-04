package softuni.car_shop.models.service_dtos;

public abstract class BaseServiceModel {

    private String id;

    public BaseServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
