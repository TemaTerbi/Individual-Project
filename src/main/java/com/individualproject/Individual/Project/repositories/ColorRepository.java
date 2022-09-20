package com.individualproject.Individual.Project.repositories;

import com.individualproject.Individual.Project.model.Color;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColorRepository extends CrudRepository<Color, Long> {
    List<Color> findByName(String name);
}
