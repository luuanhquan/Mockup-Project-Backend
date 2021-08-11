package com.repositories;

import com.entity.IssueChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IssueChangeLogRepository extends JpaRepository<IssueChangeLog, Integer> {

    @Query("from IssueChangeLog i where i.issue.id = ?1 order by i.id desc")
    List<IssueChangeLog> findByIssueId(int id);
}

