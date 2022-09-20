package com.individualproject.Individual.Project.repositories;

import com.individualproject.Individual.Project.model.Places;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlacesRepository extends CrudRepository<Places, Long> {
    List<Places> findByName(String name);
}
