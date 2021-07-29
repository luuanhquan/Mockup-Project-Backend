package com.entity.enums;

public enum REPORT_TYPE {
    WEEKLY(0),
    PROJECT(1);

    public final int value;

    REPORT_TYPE(int value) {
        this.value = value;
    }

    public static REPORT_TYPE valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (REPORT_TYPE val : REPORT_TYPE.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
