package com.entity;

import com.enums.ISSUE_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "STATUS")
    private long status;
    @OneToMany(mappedBy = "issue")
    private Collection<FileIssue> fileIssuesById;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_CREATED", referencedColumnName = "ID", nullable = false)
    private Users userCreated;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ASSIGNEE", referencedColumnName = "ID", nullable = false)
    private Users assignee;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "PARENT_ISSUE", referencedColumnName = "ID")
    private Issues parent;
    @OneToMany(mappedBy = "parent")
    private Collection<Issues> issueList;
    @OneToMany(mappedBy = "issue")
    private Collection<IssueChangeLog> issueChangeLogList;
    @OneToMany(mappedBy = "issue")
    private Collection<TimeLog> timeLogList;

    public ISSUE_STATUS getStatus() {
        return ISSUE_STATUS.valueOf((int) status);
    }

    public void setStatus(ISSUE_STATUS status) {
        this.status = status.value;
    }

}
