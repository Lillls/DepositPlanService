package com.lixxy.depositplan.dao;

import com.lixxy.depositplan.model.SaveRecordBean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Mapper
public interface SaveRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaveRecordBean record);

    int insertSelective(SaveRecordBean record);

    SaveRecordBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaveRecordBean record);

    int updateByPrimaryKey(SaveRecordBean record);

    List<SaveRecordBean> selectSaveRecordByUserIdAndPlanId(@Param("userId") Integer userId
            , @Param("planId") Integer planId);

    List<SaveRecordBean> selectSavedByPlanId(@Param("planId") Integer planId);

    SaveRecordBean selectTodayRecordByPlanId(@Param("planId") Integer planId);


    Integer getLastInsertId();

}