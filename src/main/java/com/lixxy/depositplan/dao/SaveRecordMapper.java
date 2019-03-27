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


    List<SaveRecordBean> selectSaveRecords(@Param("planId") Integer planId
            , @Param("isSaved") Integer isSaved);

    SaveRecordBean selectTodayRecordByPlanId(@Param("planId") Integer planId);


    Integer getLastInsertId();

    int saveMoney(Integer id);

}