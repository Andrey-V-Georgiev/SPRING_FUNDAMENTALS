package softuni.car_shop.models.entities;

import org.hibernate.validator.constraints.Length;
import softuni.car_shop.enums.EngineTypeEnum;
import softuni.car_shop.enums.TransmissionTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private Model model;
    private Integer year;
    private EngineTypeEnum engine;
    private TransmissionTypeEnum transmission;
    private Integer mileage;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private User seller;
    private LocalDateTime created;
    private LocalDateTime modified;

    public Offer() {
    }

    public Offer(Model model, Integer year, EngineTypeEnum engine, TransmissionTypeEnum transmission, Integer mileage, BigDecimal price, String description, String imageUrl, User seller, LocalDateTime created, LocalDateTime modified) {
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.transmission = transmission;
        this.mileage = mileage;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.seller = seller;
        this.created = created;
        this.modified = modified;
    }

    @ManyToOne
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Column(name = "year", nullable = false)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "engine", nullable = false)
    public EngineTypeEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineTypeEnum engine) {
        this.engine = engine;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false)
    public TransmissionTypeEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionTypeEnum transmission) {
        this.transmission = transmission;
    }

    @Min(value = 0, message = "Mileage must be positive number")
    @Column(name = "mileage", nullable = false)
    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @DecimalMin(value = "0", message = "Price must be positive number")
    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Length(min = 10, message = "Description must be at least 10 symbols")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToOne
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Column(name = "created", nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified", nullable = false)
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
