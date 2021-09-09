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
@Table(name = "User_Division", schema = "TEAM1", catalog = "")
public class DivisionUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "USER_DIVISION_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "IS_MANAGER")
    private boolean isManager;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "DivisionID", referencedColumnName = "ID", nullable = false)
    private Division Division;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
    private Users users;
}
