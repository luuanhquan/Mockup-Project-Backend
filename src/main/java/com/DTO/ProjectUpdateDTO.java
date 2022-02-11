package com.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProjectUpdateDTO {
    int id;
    String name;
    String description;
    Date date_start;
    Date date_end;
}


