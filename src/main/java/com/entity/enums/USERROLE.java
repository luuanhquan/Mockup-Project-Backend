package com.entity.enums;

public enum USERROLE {
    ADMIN(0),
    MANAGER(1),
    PM(2),
    MEMBER(3);

    public final int value;

    USERROLE(int value) {
        this.value = value;
    }

    public static USERROLE valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (USERROLE val : USERROLE.values()) {
            if (val.value == value) {
                return val;
            }
        }
        return null;
    }
}
