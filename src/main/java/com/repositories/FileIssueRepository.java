package com.repositories;

import com.entity.FileIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileIssueRepository extends JpaRepository<FileIssue, Integer> {
}

