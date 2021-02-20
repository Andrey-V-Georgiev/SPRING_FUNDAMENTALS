package softuni.exam_21_feb_2021.models.service;

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
