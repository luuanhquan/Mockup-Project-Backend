package com.DTO;

import com.entity.Reports;
import lombok.Data;

import java.util.Date;
@Data
public class ReportsDTO {
    String projectId;
    Integer id;
    String dateCreat;
    Boolean type;
    String creater;

    public ReportsDTO(Reports reports) {
        this.projectId = reports.getProject().getName();
        this.id = reports.getId();
        this.dateCreat = reports.getDateCreated().toString();
        this.type = reports.getDateRead()!=null;
        this.creater = reports.getUsers().getUsername();
    }
}
