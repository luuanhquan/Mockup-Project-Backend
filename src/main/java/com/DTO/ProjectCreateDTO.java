package com.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectCreateDTO {
    int id;
    String name;
    String description;
    Date date_start;
    Date date_end;
}


