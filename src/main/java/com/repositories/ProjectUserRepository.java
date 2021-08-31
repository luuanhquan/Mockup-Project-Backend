package com.repositories;

import com.entity.ProjectUser;
import com.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer> {

    @Query("select count(p) from ProjectUser p where p.project = ?1")
    public int countByProject(Projects projects);


}

