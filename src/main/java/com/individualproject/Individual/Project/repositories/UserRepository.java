package com.individualproject.Individual.Project.repositories;

import com.individualproject.Individual.Project.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String login);
}
