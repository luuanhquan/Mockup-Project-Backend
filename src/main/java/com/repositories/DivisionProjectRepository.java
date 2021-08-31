package com.repositories;

import com.entity.DivisionProject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface DivisionProjectRepository extends org.springframework.data.jpa.repository.JpaRepository<DivisionProject, Integer> {
}

