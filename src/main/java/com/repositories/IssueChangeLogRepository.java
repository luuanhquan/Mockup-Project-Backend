package com.repositories;

import com.DTO.IssueDetailDTO;
import com.DTO.SimpleIssue;
import com.entity.IssueChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface IssueChangeLogRepository extends JpaRepository<IssueChangeLog, Integer> {

    @Query("select new com.DTO.SimpleIssue(i.id, icl.title) from Issues i join IssueChangeLog icl on i.parent.id=icl.issue.id where i.parent.id = ?1 order by i.id")
    List<SimpleIssue> findSubs(int id);

    @Query("select new com.DTO.IssueDetailDTO(" +
            "icl.users.username," +
            "icl.title," +
            "icl.description," +
            "icl.dateStated," +
            "icl.dateDue," +
            "icl.type," +
            "icl.target," +
            "icl.priority," +
            "icl.process," +
            "icl.solution," +
            "icl.timespend," +
            "icl.status," +
            "icl.comments," +
            "icl.dateChanged) from Issues i join IssueChangeLog icl on i.id=icl.issue.id where i.id = ?1 order by i.id desc")
    List<IssueDetailDTO> findByIssueId(Integer id);
}

