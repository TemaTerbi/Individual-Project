package com.individualproject.Individual.Project.model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 5", min = 5, max = 100)
    String title;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 5", min = 5, max = 100)
    String description;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private AnimalType animalType;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private BreedCats breedCats;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 5", min = 5, max = 100)
    String name;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 5", min = 5, max = 100)
    String date;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Places places;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 8", min = 8, max = 100)
    String phone;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Color color;

    Long userId;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private SocialWeb socialWeb;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public BreedCats getBreedCats() {
        return breedCats;
    }

    public void setBreedCats(BreedCats breedCats) {
        this.breedCats = breedCats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Places getPlaces() {
        return places;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public SocialWeb getSocialWeb() {
        return socialWeb;
    }

    public void setSocialWeb(SocialWeb socialWeb) {
        this.socialWeb = socialWeb;
    }

    public Posts(String title, String description, AnimalType animalType, BreedCats breedCats, String name, String date, Places places, String phone, Color color, Long userId, SocialWeb socialWeb) {
        this.title = title;
        this.description = description;
        this.animalType = animalType;
        this.breedCats = breedCats;
        this.name = name;
        this.date = date;
        this.places = places;
        this.phone = phone;
        this.color = color;
        this.userId = userId;
        this.socialWeb = socialWeb;
    }

    public Posts() {
    }
}
