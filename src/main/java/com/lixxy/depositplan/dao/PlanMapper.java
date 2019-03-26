package com.lixxy.depositplan.dao;

import com.lixxy.depositplan.model.PlanBean;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public interface PlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlanBean record);

    int insertSelective(PlanBean record);

    PlanBean selectByPrimaryKey(Integer id);
    List<PlanBean> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(PlanBean record);

    int updateByPrimaryKey(PlanBean record);
}