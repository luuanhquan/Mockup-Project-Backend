package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "FILES_SEQ", allocationSize = 1)
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
    @OneToMany(mappedBy = "avatar")
    private Collection<Users> userList;
}
