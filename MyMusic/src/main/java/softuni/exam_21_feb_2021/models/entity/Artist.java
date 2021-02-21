package softuni.exam_21_feb_2021.models.entity;

import softuni.exam_21_feb_2021.enumerations.ArtistEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "artists")
public class Artist  extends BaseEntity {

    private ArtistEnum name;
    private String careerInformation;

    public Artist() {
    }

    public Artist(ArtistEnum name, String careerInformation) {
        this.name = name;
        this.careerInformation = careerInformation;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    public ArtistEnum getName() {
        return name;
    }

    public void setName(ArtistEnum name) {
        this.name = name;
    }

    @Column(name = "career_information", columnDefinition = "TEXT")
    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }
}
