package com.lixxy.depositplan.model;

public class UserIdAndPlaId {
    private int planId;
    private int UserId;

    public UserIdAndPlaId() {
    }

    public UserIdAndPlaId(int planId, int userId) {
        this.planId = planId;
        UserId = userId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
