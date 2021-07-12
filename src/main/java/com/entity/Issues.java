package com.entity;

import com.entity.enumFolder.ActiveStatus;
import com.entity.enumFolder.IssueStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "STATUS")
    private long status;

    public IssueStatus getStatus() {
        return IssueStatus.valueOf((int) status);
    }

    public void setStatus(IssueStatus status) {
        this.status = status.value;
    }
    @OneToMany(mappedBy = "issue")
    private Collection<FileIssue> fileIssuesById;
    @ManyToOne
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;
    @ManyToOne
    @JoinColumn(name = "USER_CREATED", referencedColumnName = "ID", nullable = false)
    private Users userCreated;
    @ManyToOne
    @JoinColumn(name = "ASSIGNEE", referencedColumnName = "ID", nullable = false)
    private Users assignee;
    @ManyToOne
    @JoinColumn(name = "PARENT_ISSUE", referencedColumnName = "ID", nullable = false)
    private Issues parent;
    @OneToMany(mappedBy = "parent")
    private Collection<Issues> issueList;
    @OneToMany(mappedBy = "issue")
    private Collection<IssueChangeLog> issueChangeLogList;
    @OneToMany(mappedBy = "issue")
    private Collection<TimeLog> timeLogList;

}
