package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "FILE_ISSUE", schema = "TEAM1", catalog = "")
public class FileIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "FILE_ISSUE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "FILEID", referencedColumnName = "ID", nullable = false)
    private Files file;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ISSUEID", referencedColumnName = "ID", nullable = false)
    private Issues issue;

}
