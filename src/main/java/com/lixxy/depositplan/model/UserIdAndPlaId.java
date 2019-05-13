package com.lixxy.depositplan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserIdAndPlaId {
    private int planId;
    private int UserId;

    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
