package com.enums;

public enum ISSUE_TYPE {
    BUG(0),
    REQUEST(1),
    TASK(2);


    public final int value;

    ISSUE_TYPE(int value) {
        this.value = value;
    }

    public static ISSUE_TYPE valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (ISSUE_TYPE val : ISSUE_TYPE.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
