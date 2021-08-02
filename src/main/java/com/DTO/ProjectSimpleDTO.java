package com.DTO;

import com.entity.Projects;
import lombok.Data;

@Data
public class ProjectSimpleDTO {
    int id;
    String name;

    public ProjectSimpleDTO(Projects projects) {
        this.id = projects.getId();
        this.name = projects.getName();
    }
}
