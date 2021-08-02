package com.enums;

public enum USER_ROLE {
    ADMIN(0),
    MANAGER(1),
    PM(2),
    MEMBER(3);

    public final int value;

    USER_ROLE(int value) {
        this.value = value;
    }

    public static USER_ROLE valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (USER_ROLE val : USER_ROLE.values()) {
            if (val.value == value) {
                return val;
            }
        }
        return null;
    }
}
