package com.entity.enumFolder;

public enum ActiveStatus {
    INACTIVE(0),
    ACTIVE(1);

    public final int value;

    ActiveStatus(int value) {
        this.value = value;
    }

    public static ActiveStatus valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (ActiveStatus val : ActiveStatus.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
