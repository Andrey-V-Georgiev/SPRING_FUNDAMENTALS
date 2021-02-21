package softuni.exam_21_feb_2021.models.service;

import softuni.exam_21_feb_2021.enumerations.ArtistEnum;

public class ArtistServiceModel extends  BaseServiceModel {

    private ArtistEnum name;
    private String careerInformation;

    public ArtistServiceModel() {
    }

    public ArtistServiceModel(ArtistEnum name, String careerInformation) {
        this.name = name;
        this.careerInformation = careerInformation;
    }

    public ArtistEnum getName() {
        return name;
    }

    public void setName(ArtistEnum name) {
        this.name = name;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }
}
