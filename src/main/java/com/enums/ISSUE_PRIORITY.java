package com.enums;

public enum ISSUE_PRIORITY {
    MINOR(0),
    LOW(1),
    NORMAL(2),
    HIGH(3),
    URGE(4);


    public final int value;

    ISSUE_PRIORITY(int value) {
        this.value = value;
    }

    public static ISSUE_PRIORITY valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (ISSUE_PRIORITY val : ISSUE_PRIORITY.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
