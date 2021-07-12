package com.repositories;

import com.entity.IssueChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IssueChangeLogRepository extends JpaRepository<IssueChangeLog, Integer> {
}

