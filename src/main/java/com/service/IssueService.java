package com.service;

import com.DTO.IssueCreate;
import com.DTO.IssueDTO;
import com.DTO.IssueModelData;
import com.DTO.IssueSummaryDTO;
import com.entity.Issues;
import com.repositories.IssueChangeLogRepository;
import com.repositories.IssueRepository;
import com.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IssueService {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    IssueChangeLogRepository issueChangeLogRepository;

    @Autowired
    FileIssueService fileIssueService;
    private static final IssueModelData modelData= new IssueModelData();

    public Issues save(Issues s) {
        return (Issues) issueRepository.findAll();
    }


    public List<Issues> findAll() {
        return issueRepository.findIssuesRoot();
    }


    public void deleteById(Integer id) {
        issueRepository.deleteById(id);
    }


    public IssueDTO findById(Integer id) {

        IssueDTO issueDTO= issueRepository.findByIssueId(id).stream().findFirst().orElse(null);
//
        if(!ObjectUtil.isEmpty(issueDTO)){
            issueDTO.setSubs(issueChangeLogRepository.findSubs(issueDTO.getId()));
            issueDTO.setFiles(fileIssueService.findByIssueId(issueDTO.getId()));
            issueDTO.setModelData(this.modelData);
            issueDTO.setIssueChangeLogDTO(issueChangeLogRepository.findByIssueId(issueDTO.getId()));
        }
        return issueDTO;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UsersService usersService;
    public void saveNew(IssueCreate issueDTO) {
        issueDTO.setCreatorID(usersService.getUserLogin().getId());
        entityManager.createNativeQuery("INSERT INTO ISSUES (PROJECTID, USER_CREATED, ASSIGNEE, PARENT_ISSUE, STATUS) VALUES (?,?,?,?,?)")
                .setParameter(1, issueDTO.getProjectID())
                .setParameter(2, issueDTO.getCreatorID())
                .setParameter(3, issueDTO.getAssigneeID())
                .setParameter(4, issueDTO.getParentID())
                .setParameter(5, 1).executeUpdate();
        int issue= ((BigDecimal) entityManager.createNativeQuery("select i.ID from ISSUES i order by i.ID desc ")
                .setMaxResults(1).getResultList().get(0)).intValue();
        System.out.println(issue);
        entityManager.createNativeQuery("INSERT INTO ISSUE_CHANGE_LOG " +
                        "(ISSUEID, USER_MODIFIED, TITLE, DESCRIPTION, DATE_CHANGED, " +
                        "DATE_STATED, DATE_DUE, TYPE, TARGET, PRIORITY, PROCESS, " +
                        "SOLUTION, TIMESPEND, STATUS, COMMENTS) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(1, issue )
                .setParameter(2, issueDTO.getCreatorID() )
                .setParameter(3, issueDTO.getTitle() )
                .setParameter(4, issueDTO.getDescription())
                .setParameter(5, new Date())
                .setParameter(6, issueDTO.getStarted())
                .setParameter(7, issueDTO.getDue())
                .setParameter(8, issueDTO.getType())
                .setParameter(9, issueDTO.getTarget() )
                .setParameter(10, issueDTO.getPriority() )
                .setParameter(11, issueDTO.getProcess() )
                .setParameter(12, issueDTO.getSolution() )
                .setParameter(13, issueDTO.getTimespent() )
                .setParameter(14, issueDTO.getStatus() )
                .setParameter(15, issueDTO.getComments() )
                .executeUpdate();
    }

    public List<IssueSummaryDTO> findByProject(Integer id) {
        return issueRepository.findByProject(id);
    }
}
