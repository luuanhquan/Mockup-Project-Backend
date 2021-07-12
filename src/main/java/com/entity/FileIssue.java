package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "FILE_ISSUE", schema = "TEAM1", catalog = "")
public class FileIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "FILEID", referencedColumnName = "ID", nullable = false)
    private Files file;
    @ManyToOne
    @JoinColumn(name = "ISSUEID", referencedColumnName = "ID", nullable = false)
    private Issues issue;

}
