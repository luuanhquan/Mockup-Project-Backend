package com.entity.enumFolder;

public enum IssueStatus {
    NEW(0),
    IN_PROGRESS(1),
    RESOLVE(2),
    BUILD(3),
    REJECT(4),
    CLOSE(5),
    WATCHING(6);

    public final int value;

    IssueStatus(int value) {
        this.value = value;
    }

    public static IssueStatus valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (IssueStatus val : IssueStatus.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
