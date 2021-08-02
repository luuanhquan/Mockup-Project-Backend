package com.entity.enums;

public enum UNIT_TYPE {
    HOUR(0),
    DAY(1);


    public final int value;

    UNIT_TYPE(int value) {
        this.value = value;
    }

    public static UNIT_TYPE valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (UNIT_TYPE val : UNIT_TYPE.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
