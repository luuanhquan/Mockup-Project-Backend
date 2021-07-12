package com.repositories;

import com.entity.FileProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileProjectRepository extends JpaRepository<FileProject, Integer> {
}

