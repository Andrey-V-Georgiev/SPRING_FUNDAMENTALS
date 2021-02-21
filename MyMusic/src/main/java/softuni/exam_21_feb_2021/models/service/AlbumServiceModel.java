package softuni.exam_21_feb_2021.models.service;

import softuni.exam_21_feb_2021.enumerations.GenreEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumServiceModel extends BaseServiceModel {

    private String name;
    private String imgUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private String producer;
    private GenreEnum genre;
    private ArtistServiceModel artist;
    private UserServiceModel addedFrom;

    public AlbumServiceModel() {
    }

    public AlbumServiceModel(String name, String imgUrl, String description, Integer copies, BigDecimal price, LocalDate releaseDate, String producer, GenreEnum genre, ArtistServiceModel artist, UserServiceModel addedFrom) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.copies = copies;
        this.price = price;
        this.releaseDate = releaseDate;
        this.producer = producer;
        this.genre = genre;
        this.artist = artist;
        this.addedFrom = addedFrom;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @Size(min = 5,  message = "ImageUrl must be at least 5 symbols")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @NotBlank
    @Size(min = 5,  message = "Description must be at least 5 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Min(value = 10, message = "Copies must be at least 5 symbols")
    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    @DecimalMin(value = "0", message = "Price must be positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @PastOrPresent(message = "ReleaseDate cannot be in the future")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Enumerated(EnumType.STRING)
    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    public ArtistServiceModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistServiceModel artist) {
        this.artist = artist;
    }

    public UserServiceModel getAddedFrom() {
        return addedFrom;
    }

    public void setAddedFrom(UserServiceModel addedFrom) {
        this.addedFrom = addedFrom;
    }
}
