package com.repositories;

import com.entity.DivisionProject;
import org.springframework.stereotype.Repository;


@Repository
public interface DivisionProjectRepository extends org.springframework.data.jpa.repository.JpaRepository<DivisionProject, Integer> {

}

