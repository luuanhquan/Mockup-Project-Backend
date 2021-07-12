package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "FILE_PROJECT", schema = "TEAM1", catalog = "")
public class FileProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "CATEGORY")
    private long category;
    @ManyToOne
    @JoinColumn(name = "FILE_ID", referencedColumnName = "ID", nullable = false)
    private Files file;
    @ManyToOne
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;
}
