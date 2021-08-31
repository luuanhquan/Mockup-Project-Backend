package com.repositories;

import com.entity.FileProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface FileProjectRepository extends JpaRepository<FileProject, Integer> {
}

