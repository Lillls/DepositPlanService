package com.lixxy.depositplan.model;

public class PlanDetailBean {
    private int id;
    private String name;

    private int passDays;
    private int totalDays;

    private Long savedMoney;
    private Long totalMoney;

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

    public Long getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(Long savedMoney) {
        this.savedMoney = savedMoney;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }
}
