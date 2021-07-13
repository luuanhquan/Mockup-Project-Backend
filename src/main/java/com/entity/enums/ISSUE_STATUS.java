package com.entity.enums;

public enum ISSUE_STATUS {
    NEW(0),
    IN_PROGRESS(1),
    RESOLVE(2),
    BUILD(3),
    REJECT(4),
    CLOSE(5),
    WATCHING(6);

    public final int value;

    ISSUE_STATUS(int value) {
        this.value = value;
    }

    public static ISSUE_STATUS valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (ISSUE_STATUS val : ISSUE_STATUS.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
