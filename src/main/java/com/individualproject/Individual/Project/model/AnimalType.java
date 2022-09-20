package com.individualproject.Individual.Project.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class AnimalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Строка не может быть меньше 2", min = 2, max = 100)
    String name;

    @OneToMany(mappedBy = "animalType", fetch = FetchType.EAGER)
    private Collection<Posts> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Posts> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Posts> posts) {
        this.posts = posts;
    }

    public AnimalType(String name, Collection<Posts> posts) {
        this.name = name;
        this.posts = posts;
    }

    public AnimalType() {
    }
}
