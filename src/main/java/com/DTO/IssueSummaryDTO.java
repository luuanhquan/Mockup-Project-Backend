package com.DTO;

import com.entity.Issues;
import lombok.Data;

@Data()
public class IssueSummaryDTO {
    int id;
    String Asignee;
    String Creator;

    public IssueSummaryDTO(Issues issues) {
        this.id = issues.getId();
        this.Asignee = issues.getAssignee().getUsername();
        this.Creator = issues.getUserCreated().getUsername();
    }
}
