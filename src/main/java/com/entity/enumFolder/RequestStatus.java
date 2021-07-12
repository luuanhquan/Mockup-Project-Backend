package com.entity.enumFolder;

public enum RequestStatus {
    PENDING(0),
    APPROVE(1),
    DENIED(2),
    CANCELED(3);

    public final int value;

    RequestStatus(int value) {
        this.value = value;
    }

    public static RequestStatus valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (RequestStatus val : RequestStatus.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
