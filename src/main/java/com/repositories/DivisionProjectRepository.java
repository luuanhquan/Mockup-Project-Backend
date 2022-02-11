package com.repositories;

import com.entity.DivisionProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface DivisionProjectRepository extends JpaRepository<DivisionProject, Integer> {
}

