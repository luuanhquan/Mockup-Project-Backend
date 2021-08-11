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
@Table(name = "DIVISION_PROJECT", schema = "TEAM1", catalog = "")
public class DivisionProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "DIVISIONID", referencedColumnName = "ID", nullable = false)
    private Division division;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;


}
