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
@Table(name = "PROJECT_USER", schema = "TEAM1", catalog = "")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "ISPM")
    private boolean ispm;
    @ManyToOne
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;
    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
    private Users users;
}
