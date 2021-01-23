package softuni.car_shop.models.service_dtos;

import softuni.car_shop.enums.CoupeTypeEnum;
import softuni.car_shop.enums.EngineTypeEnum;
import softuni.car_shop.enums.TransmissionTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OfferServiceModel extends BaseServiceModel {

    private ModelServiceModel model;
    private Integer year;
    private EngineTypeEnum engine;
    private TransmissionTypeEnum transmission;
    private Integer mileage;
    private CoupeTypeEnum category;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private UserServiceModel seller;
    private LocalDateTime created;
    private LocalDateTime modified;

    public OfferServiceModel() {
    }

    public OfferServiceModel(ModelServiceModel model, Integer year, EngineTypeEnum engine, TransmissionTypeEnum transmission, Integer mileage, CoupeTypeEnum category, BigDecimal price, String description, String imageUrl, UserServiceModel seller, LocalDateTime created, LocalDateTime modified) {
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.transmission = transmission;
        this.mileage = mileage;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.seller = seller;
        this.created = created;
        this.modified = modified;
    }

    public ModelServiceModel getModel() {
        return model;
    }

    public void setModel(ModelServiceModel model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public EngineTypeEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineTypeEnum engine) {
        this.engine = engine;
    }

    public TransmissionTypeEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionTypeEnum transmission) {
        this.transmission = transmission;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public CoupeTypeEnum getCategory() {
        return category;
    }

    public void setCategory(CoupeTypeEnum category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserServiceModel getSeller() {
        return seller;
    }

    public void setSeller(UserServiceModel seller) {
        this.seller = seller;
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
