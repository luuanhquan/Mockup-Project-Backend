package com.repositories;

import com.entity.Division;
import org.springframework.stereotype.Repository;


@Repository
public interface DivisionProjectRepository extends org.springframework.data.jpa.repository.JpaRepository<Division, Integer> {
}

