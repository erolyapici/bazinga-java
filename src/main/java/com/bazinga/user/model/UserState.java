package com.bazinga.user.model;

public enum UserState {
    ACTIVE(1), PASSIVE(2),WAITING_APPROVE(3);
    private Integer state;

    UserState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
}
