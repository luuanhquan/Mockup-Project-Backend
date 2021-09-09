package com.entity;

import com.enums.ISSUE_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "ISSUES_SEQ", allocationSize = 1)
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
    private List<Issues> issueList;
    @OneToMany(mappedBy = "issue")
    private List<IssueChangeLog> issueChangeLogList;
    public List<IssueChangeLog> getIssueChangeLogList() {
        return issueChangeLogList.stream().sorted(new Comparator<IssueChangeLog>() {
            @Override
            public int compare(IssueChangeLog o1, IssueChangeLog o2) {
                return o2.getId()-o1.getId();
            }
        }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "issue")
    private Collection<TimeLog> timeLogList;

    public ISSUE_STATUS getStatus() {
        return ISSUE_STATUS.valueOf((int) status);
    }

    public void setStatus(ISSUE_STATUS status) {
        this.status = status.value;
    }

    @Override
    public String toString(){
        return this.id.toString();
    }

}
