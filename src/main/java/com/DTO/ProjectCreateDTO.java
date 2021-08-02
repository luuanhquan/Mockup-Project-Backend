package com.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectCreateDTO {
    String name;
    String des;
    Date date_start;
    Date date_end;
    String status;
}


