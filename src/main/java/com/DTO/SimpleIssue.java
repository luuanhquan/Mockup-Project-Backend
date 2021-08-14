package com.DTO;

import com.entity.Issues;
import lombok.Data;

@Data
public class SimpleIssue {
    int id;
    String name;

    public SimpleIssue loadFromIssue(Issues issue) {
        this.id = issue.getId();
        this.name = issue.getIssueChangeLogList().get(0).getTitle();
        return this;
    }
}
