package com.DTO;

import com.entity.IssueChangeLog;
import com.enums.ISSUE_PRIORITY;
import com.enums.ISSUE_STATUS;
import com.enums.ISSUE_TARGET;
import com.enums.ISSUE_TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.Hours;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
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

    public IssueDetailDTO(String user, String title, String description, Date date_start, Date date_due, int type, int target, int priority, int process, String solution, Long timespend, int status, String comment, Date dateChanged) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.date_start = date_start;
        this.date_due = date_due;
        this.type = ISSUE_TYPE.valueOf(type).toString();
        this.target = ISSUE_TARGET.valueOf(target).name();
        this.priority = ISSUE_PRIORITY.valueOf(priority).name();
        this.process = process;
        this.solution = solution;
        this.timespend = timespend;
        this.status = ISSUE_STATUS.valueOf(status).name();
        this.comment = comment;
        int changedTime =Hours.hoursBetween( new DateTime(dateChanged),new DateTime()).getHours();
        int days= changedTime/24;
        int hours= changedTime%24;
        this.changedTime= days>0?days+" day(s) "+hours+ " hour(s) ago":hours+ " hour(s) ago";
    }
//    public IssueDetailDTO loadFromIssueChangeLog(IssueChangeLog issueChangeLog) {
//        this.user=issueChangeLog.getUsers().getUsername();
//        this.title=issueChangeLog.getTitle();
//        this.description=issueChangeLog.getDescription();
//        this.date_start=issueChangeLog.getDateStated();
//        this.date_due=issueChangeLog.getDateDue();
//        this.type=issueChangeLog.getType()==null?null:issueChangeLog.getType().name();
//        this.target=issueChangeLog.getTarget()==null?null:issueChangeLog.getTarget().name();
//        this.priority=issueChangeLog.getPriority()==null?null:issueChangeLog.getPriority().name();
//        this.process=issueChangeLog.getProcess();
//        this.solution=issueChangeLog.getSolution();
//        this.timespend=issueChangeLog.getTimespend();
//        this.status=issueChangeLog.getStatus()==null?null:issueChangeLog.getStatus().name();
//        this.comment=issueChangeLog.getComments();
//        int changedTime =Hours.hoursBetween( new DateTime(issueChangeLog.getDateChanged()),new DateTime()).getHours();
//        int days= changedTime/24;
//        int hours= changedTime%24;
//        this.changedTime= days>0?days+" day(s) "+hours+ " hour(s) ago":hours+ " hour(s) ago";
//
//        return this;
//    }
}
