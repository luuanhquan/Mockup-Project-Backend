package com.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class IssueCreate {
    int creatorID;
    int assigneeID;
    Integer parentID;
    int projectID;
    String title;
    String description;
    Date started;
    Date due;
    int type;
    int target;
    int priority;
    double process;
    String solution;
    double timespent;
    int status;
    String comments;
}
