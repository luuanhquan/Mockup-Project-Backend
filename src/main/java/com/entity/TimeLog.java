package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TIME_LOG", schema = "TEAM1", catalog = "")
public class TimeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "TIME_LOG_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE_LOG")
    private Date dateLog;
    @Column(name = "TIME_LOG")
    private long timeLog;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ISSUEID", referencedColumnName = "ID", nullable = false)
    private Issues issue;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
    private Users users;
}
