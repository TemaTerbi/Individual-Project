package com.individualproject.Individual.Project.repositories;

import com.individualproject.Individual.Project.model.Posts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostsRepository extends CrudRepository<Posts, Long> {
    List<Posts> findByUserId(Long id);
    public List<Posts> findByTitleContains(String title);
}
