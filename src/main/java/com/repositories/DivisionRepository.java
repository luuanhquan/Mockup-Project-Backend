package com.repositories;

import com.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface DivisionRepository extends JpaRepository<Division, Integer> {
}

