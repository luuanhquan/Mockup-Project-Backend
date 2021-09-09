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
@Table(name = "PROJECT_USER", schema = "TEAM1", catalog = "")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "PROJECT_USER_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ISPM")
    private boolean ispm;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
    private Users users;
}
