package com.individualproject.Individual.Project.repositories;

import com.individualproject.Individual.Project.model.AnimalType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimaTypeRepository extends CrudRepository<AnimalType, Long> {
    List<AnimalType> findByName(String name);
}
