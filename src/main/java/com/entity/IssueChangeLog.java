package com.entity;

import com.entity.enumFolder.ActiveStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ISSUE_CHANGE_LOG", schema = "TEAM1", catalog = "")
public class IssueChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
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
    private Date type;
    @Column(name = "TARGET")
    private Date target;
    @Column(name = "PRIORITY")
    private Date priority;
    @Column(name = "PROCESS")
    private Date process;
    @Column(name = "SOLUTION")
    private String solution;
    @Column(name = "TIMESPEND")
    private Long timespend;
    @Column(name = "STATUS")
    private long status;
    public ActiveStatus getStatus() {
        return ActiveStatus.valueOf((int) status);
    }

    public void setStatus(ActiveStatus status) {
        this.status = status.value;
    }
    @Column(name = "COMMENTS")
    private String comments;
    @ManyToOne
    @JoinColumn(name = "ISSUEID", referencedColumnName = "ID", nullable = false)
    private Issues issue;
    @ManyToOne
    @JoinColumn(name = "USER_MODIFIED", referencedColumnName = "ID", nullable = false)
    private Users users;
}
