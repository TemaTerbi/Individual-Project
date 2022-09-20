package com.individualproject.Individual.Project.repositories;

import com.individualproject.Individual.Project.model.SocialWeb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocialWebRepository extends CrudRepository<SocialWeb, Long> {
    List<SocialWeb> findByName(String name);
}
