package com.entity.enums;

public enum USER_ROLE {
    ROLE_ADMIN(0),
    ROLE_MANAGER(1),
    ROLE_PM(2),
    ROLE_MEMBER(3);

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

    static String role(USER_ROLE role){
        return role.name().substring(6);
    }
}
