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
@Table(name = "FILE_PROJECT", schema = "TEAM1", catalog = "")
public class FileProject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "FILE_PROJECT_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CATEGORY")
    private long category;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "FILE_ID", referencedColumnName = "ID", nullable = false)
    private Files file;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;
}
