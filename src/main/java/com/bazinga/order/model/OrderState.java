package com.bazinga.order.model;

public enum OrderState {
    APPROVED(1);
    private Integer state;

    OrderState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
