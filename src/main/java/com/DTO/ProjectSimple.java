package com.DTO;

import com.entity.Projects;
import lombok.Data;

@Data
public class ProjectSimple {
    int id;
    String name;

    public ProjectSimple(Projects projects){
        this.id=projects.getId();
        this.name=projects.getName();
    }
}
