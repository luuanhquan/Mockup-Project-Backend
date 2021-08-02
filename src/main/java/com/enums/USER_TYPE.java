package com.enums;

public enum USER_TYPE {
    THU_VIEC(0),
    THUC_TAP(1),
    NHAN_VIEN_CHINH_THUC(2);


    public final int value;

    USER_TYPE(int value) {
        this.value = value;
    }

    public static USER_TYPE valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (USER_TYPE val : USER_TYPE.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
