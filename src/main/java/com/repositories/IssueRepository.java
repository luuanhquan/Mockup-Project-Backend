package com.repositories;

import com.DTO.IssueDTO;
import com.DTO.IssueSummaryDTO;
import com.entity.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface IssueRepository extends JpaRepository<Issues, Integer> {
    @Query("from Issues i  where i.parent is NULL ")
    public List<Issues> findIssuesRoot();

    @Query("select  new com.DTO.IssueDTO( i.id ," +
            "i.userCreated.username ," +
            "i.assignee.username ," +
            "i.status," +
            "i.parent.id, icl.title) from Issues i join IssueChangeLog icl on i.id = icl.issue.id where i.id=?1")
    List<IssueDTO> findByIssueId(Integer id);



    @Query("select new com.DTO.IssueSummaryDTO(i.id, i.assignee.username, i.userCreated.username) from Issues i where i.project.id=?1")
    List<IssueSummaryDTO> findByProject(Integer id);
}

