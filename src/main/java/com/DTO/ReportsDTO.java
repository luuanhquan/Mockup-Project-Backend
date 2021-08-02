package com.DTO;

import com.entity.Reports;
import lombok.Data;

import java.util.Date;

@Data
public class ReportsDTO {
    String projectId;
    Integer id;
    String dateCreat;
    String type;
    String creater;
    Boolean status;
    String advantage;
    String disadvantage;
    String difficuly;
    String propose;

    Date dateCreated;


    public ReportsDTO(Reports reports) {
        this.projectId = reports.getProject().getName();
        this.id = reports.getId();
        this.dateCreat = reports.getDateCreated().toString();
        this.type = reports.getType().name();
        this.creater = reports.getUsers().getUsername();
        this.status = reports.getDateRead() != null;
        this.advantage = reports.getAdvantage();
        this.disadvantage = reports.getDisadvantage();
        this.difficuly = reports.getDifficulty();
        this.propose = reports.getPropose();
    }
}
