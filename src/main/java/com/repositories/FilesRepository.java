package com.repositories;

import com.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilesRepository extends JpaRepository<Files, Integer> {
}

