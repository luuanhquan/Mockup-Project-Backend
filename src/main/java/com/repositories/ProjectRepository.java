package com.repositories;

import com.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Projects, Integer> {
    List<Projects> findAll();
    @Query("select count(p.id) from Projects p")
    long getAlProject();

}

