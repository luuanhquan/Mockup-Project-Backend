package com.entity;

import com.DTO.ReportsDTO;
import com.entity.enums.ISSUE_STATUS;
import com.entity.enums.REPORT_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.ProjectService;
import com.service.UsersService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "REPORTS")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
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
    @JsonIgnore
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID", nullable = false)
    private Projects project;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
    private Users users;
    @Column(name = "TYPE")
    private int type;

    public REPORT_TYPE getType() {
        return REPORT_TYPE.valueOf((int) type);
    }

    public void setType(REPORT_TYPE type) {
        this.type = type.value;
    }

    public Reports(ReportsDTO dto) throws ParseException {
        this.project = new ProjectService().findById(Integer.valueOf(dto.getProjectId())).orElse(null);
        this.advantage = dto.getAdvantage();
        this.disadvantage = dto.getDisadvantage();
        this.difficulty = dto.getDifficuly();
        this.propose = dto.getPropose();
        this.users = new UsersService().findByUsername("quan");
        this.type = REPORT_TYPE.valueOf(dto.getType()).value;
        this.dateCreated= this.getDate(dto.getDateCreat());
    }

    private Date getDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
}
