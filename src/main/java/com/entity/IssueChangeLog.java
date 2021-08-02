package com.entity;

import com.enums.ACTIVE_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ISSUE_CHANGE_LOG", schema = "TEAM1", catalog = "")
public class IssueChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public ACTIVE_STATUS getStatus() {
        return ACTIVE_STATUS.valueOf((int) status);
    }

    public void setStatus(ACTIVE_STATUS status) {
        this.status = status.value;
    }
}
