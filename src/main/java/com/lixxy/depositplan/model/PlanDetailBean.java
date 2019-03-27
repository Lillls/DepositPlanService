package com.lixxy.depositplan.model;

public class PlanDetailBean {
    private int id;
    private String name;

    private int passDays;
    private int totalDays;

    private Double savedMoney;
    private Double totalMoney;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassDays() {
        return passDays;
    }

    public void setPassDays(int passDays) {
        this.passDays = passDays;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public Double getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(Double savedMoney) {
        this.savedMoney = savedMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
