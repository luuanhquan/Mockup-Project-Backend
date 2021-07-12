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
@Table(name = "TIME_LOG", schema = "TEAM1", catalog = "")
public class TimeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "DATE_LOG")
    private Date dateLog;
    @Column(name = "TIME_LOG")
    private long timeLog;
    @ManyToOne
    @JoinColumn(name = "ISSUEID", referencedColumnName = "ID", nullable = false)
    private Issues issue;
    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
    private Users users;
}
