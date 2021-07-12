package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "ADVANTAGE")
    private String advantage;
    @Column(name = "DISADVANTAGE")
    private String disadvantage;
    @Column(name = "DIFFICULTY")
    private String difficulty;
    @Column(name = "PROPOSE")
    private String propose;
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @Column(name = "DATE_READ")
    private Date dateRead;
    @ManyToOne
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;
    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
    private Users users;

}
