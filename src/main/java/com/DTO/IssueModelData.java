package com.DTO;

import com.enums.ISSUE_PRIORITY;
import com.enums.ISSUE_STATUS;
import com.enums.ISSUE_TARGET;
import com.enums.ISSUE_TYPE;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class IssueModelData {
    String[] typeList;
    String[] priorityList;
    String[] targetList;
    String[] statusList;

    public IssueModelData(){
        this.typeList=Arrays.stream(ISSUE_TYPE.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        this.priorityList=Arrays.stream(ISSUE_PRIORITY.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        this.targetList=Arrays.stream(ISSUE_TARGET.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        this.statusList=Arrays.stream(ISSUE_STATUS.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
