package com.repositories;

import com.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer> {
}

