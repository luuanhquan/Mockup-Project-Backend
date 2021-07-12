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
@Table(name = "DIVISION_PROJECT", schema = "TEAM1", catalog = "")
public class DivisionProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "DIVISIONID", referencedColumnName = "ID", nullable = false)
    private Division division;
    @ManyToOne
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;




}
