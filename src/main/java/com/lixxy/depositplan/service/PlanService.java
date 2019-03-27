package com.lixxy.depositplan.service;

import com.lixxy.depositplan.dao.PlanMapper;
import com.lixxy.depositplan.model.PlanBean;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlanService {
    @Autowired
    PlanMapper planMapper;

    public void insertSelective(PlanBean planBean) {
        planMapper.insertSelective(planBean);
    }

    public PlanBean selectByPrimaryKey(Integer planId) {
        return planMapper.selectByPrimaryKey(planId);
    }

    public List<PlanBean> selectByUserId(Integer userId) {
        return planMapper.selectByUserId(userId);
    }
}
