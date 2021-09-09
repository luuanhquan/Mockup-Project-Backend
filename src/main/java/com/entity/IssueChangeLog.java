package com.entity;

import com.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ISSUE_CHANGE_LOG", schema = "TEAM1")
public class IssueChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "ISSUE_CHANGE_LOG_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DATE_CHANGED")
    private Date dateChanged;
    @Column(name = "DATE_STATED")
    private Date dateStated;
    @Column(name = "DATE_DUE")
    private Date dateDue;
    @Column(name = "TYPE")
    private int type;
    public ISSUE_TYPE getType() {
        return ISSUE_TYPE.valueOf(type);
    }

    public void setType(ISSUE_TYPE type) {
        this.type = type.value;
    }
    @Column(name = "TARGET")
    private int target;
    public ISSUE_TARGET getTarget() {
        return ISSUE_TARGET.valueOf( target);
    }

    public void setTarget(ISSUE_TARGET target) {
        this.target = target.value;
    }
    @Column(name = "PRIORITY")
    private int priority;
    public ISSUE_PRIORITY getPriority() {
        return ISSUE_PRIORITY.valueOf( priority);
    }

    public void setPriority(ISSUE_PRIORITY priority) {
        this.priority = priority.value;
    }
    @Column(name = "PROCESS")
    private int process;
    @Column(name = "SOLUTION")
    private String solution;
    @Column(name = "TIMESPEND")
    private Long timespend;
    @Column(name = "STATUS")
    private int status;
    public ISSUE_STATUS getStatus() {
        return ISSUE_STATUS.valueOf( status);
    }

    public void setStatus(ISSUE_STATUS status) {
        this.status = status.value;
    }
    @Column(name = "COMMENTS")
    private String comments;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ISSUEID", referencedColumnName = "ID", nullable = false)
    private Issues issue;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_MODIFIED", referencedColumnName = "ID", nullable = false)
    private Users users;

    public String toString(){
        return this.id.toString();
    }
}
