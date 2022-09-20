package com.individualproject.Individual.Project.repositories;

import com.individualproject.Individual.Project.model.BreedCats;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BreedCatsRepository extends CrudRepository<BreedCats, Long> {
    List<BreedCats> findByName(String name);
}
