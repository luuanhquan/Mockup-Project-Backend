package com.repositories;

import com.entity.Division;
import com.entity.ProjectUser;
import com.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer> {

    @Query("select count(p) from ProjectUser p where p.project = ?1")
    public int countByProject(Projects projects);


}

