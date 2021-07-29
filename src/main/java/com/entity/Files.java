package com.entity;

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
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PATH")
    private String path;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "UPLOADER", referencedColumnName = "ID", nullable = false)
    private Users uploader;
    @OneToMany(mappedBy = "file")
    private Collection<FileIssue> fileList;
    @OneToMany(mappedBy = "file")
    private Collection<FileProject> fileProjectsList;
}
