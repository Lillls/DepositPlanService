package com.lixxy.depositplan.model;

import java.util.Date;


public class PlanBean {
    private Integer id;

    private Integer userId;
    /**
     * type 0 代表设置总钱数和结束时间,自动算每天存多少钱
     * type 1 代表每天存多少钱和结束时间,自动算总数
     * type 2 代表每天存钱,不设上限,
     * type 3 输入起始金额,输入每天钱数递增金额,输入天数.
     * type 4 输入起始金额,输入每天钱数递增金额,输入天数,每天的钱数随机.
     */
    private Integer type;

    private String name;

    private Long money;

    private Long dailyMoney;

    private Long startMoney;

    private Long increaseMoney;

    private String isDel;

    private Date createDate;

    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getDailyMoney() {
        return dailyMoney;
    }

    public void setDailyMoney(Long dailyMoney) {
        this.dailyMoney = dailyMoney;
    }

    public Long getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(Long startMoney) {
        this.startMoney = startMoney;
    }

    public Long getIncreaseMoney() {
        return increaseMoney;
    }

    public void setIncreaseMoney(Long increaseMoney) {
        this.increaseMoney = increaseMoney;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}