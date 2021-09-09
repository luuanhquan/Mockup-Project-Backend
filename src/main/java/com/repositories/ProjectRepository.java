package com.repositories;

import com.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Projects, Integer> {

    @Query("from Projects p where p.status=1")
    List<Projects> findAllActive();
}



