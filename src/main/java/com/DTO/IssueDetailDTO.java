package com.DTO;

import com.entity.IssueChangeLog;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.Hours;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class IssueDetailDTO {
    String user;
    String title;
    String description;
    Date date_start;
    Date date_due;
    String type;
    String target;
    String priority;
    Integer process;
    String solution;
    Long timespend;
    String status;
    String comment;
    String changedTime;


    public IssueDetailDTO loadFromIssueChangeLog(IssueChangeLog issueChangeLog) {
        this.user=issueChangeLog.getUsers().getUsername();
        this.title=issueChangeLog.getTitle();
        this.description=issueChangeLog.getDescription();
        this.date_start=issueChangeLog.getDateStated();
        this.date_due=issueChangeLog.getDateDue();
        this.type=issueChangeLog.getType()==null?null:issueChangeLog.getType().name();
        this.target=issueChangeLog.getTarget()==null?null:issueChangeLog.getTarget().name();
        this.priority=issueChangeLog.getPriority()==null?null:issueChangeLog.getPriority().name();
        this.process=issueChangeLog.getProcess();
        this.solution=issueChangeLog.getSolution();
        this.timespend=issueChangeLog.getTimespend();
        this.status=issueChangeLog.getStatus()==null?null:issueChangeLog.getStatus().name();
        this.comment=issueChangeLog.getComments();
        int changedTime =Hours.hoursBetween( new DateTime(issueChangeLog.getDateChanged()),new DateTime()).getHours();
        int days= changedTime/24;
        int hours= changedTime%24;
        this.changedTime= days>0?days+" day(s) "+hours+ " hour(s) ago":hours+ " hour(s) ago";

        return this;
    }
}
