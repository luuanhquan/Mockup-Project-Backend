package com.repositories;

import com.entity.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IssueRepository extends JpaRepository<Issues, Integer> {
}

