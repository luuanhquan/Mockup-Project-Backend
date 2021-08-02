package com.DTO;

import com.entity.Issues;
import com.entity.ProjectUser;
import com.entity.Projects;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProjectCreateDTO {
    String name;
    String des;
    Date date_start;
    Date date_end;
    String status;
}


