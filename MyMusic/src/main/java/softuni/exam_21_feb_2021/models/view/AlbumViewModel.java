package softuni.exam_21_feb_2021.models.view;

import softuni.exam_21_feb_2021.enumerations.GenreEnum;
import softuni.exam_21_feb_2021.models.service.ArtistServiceModel;
import softuni.exam_21_feb_2021.models.service.UserServiceModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumViewModel extends BaseViewModel {

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

    public AlbumViewModel() {
    }

    public AlbumViewModel(String name, String imgUrl, String description, Integer copies, BigDecimal price, LocalDate releaseDate, String producer, GenreEnum genre, ArtistServiceModel artist, UserServiceModel addedFrom) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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
