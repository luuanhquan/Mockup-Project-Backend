package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileProjectRepository extends JpaRepository<FileProjectRepository, Integer> {
}

