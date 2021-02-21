package softuni.exam_21_feb_2021.models.binding;

import org.springframework.format.annotation.DateTimeFormat;
import softuni.exam_21_feb_2021.enumerations.ArtistEnum;
import softuni.exam_21_feb_2021.enumerations.GenreEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddBindingModel {

    private String name;
    private String imgUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private String producer;
    private GenreEnum genre;
    private ArtistEnum artist;

    public AlbumAddBindingModel() {
    }

    public AlbumAddBindingModel(String name, String imgUrl, String description, Integer copies, BigDecimal price, LocalDate releaseDate, String producer, GenreEnum genre, ArtistEnum artist) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.copies = copies;
        this.price = price;
        this.releaseDate = releaseDate;
        this.producer = producer;
        this.genre = genre;
        this.artist = artist;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @Enumerated(EnumType.STRING)
    public ArtistEnum getArtist() {
        return artist;
    }

    public void setArtist(ArtistEnum artist) {
        this.artist = artist;
    }
}
