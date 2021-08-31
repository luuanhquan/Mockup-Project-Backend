package com.DTO;

import com.entity.Issues;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleIssue {
    Integer id;
    String name;

}
