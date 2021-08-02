package com.repositories;

import com.entity.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IssueRepository extends JpaRepository<Issues, Integer> {
    @Query("from Issues i  where i.parent is NULL ")
    public List<Issues> findIssuesRoot();
}

