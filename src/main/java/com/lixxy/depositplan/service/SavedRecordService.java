package com.lixxy.depositplan.service;

import com.lixxy.depositplan.dao.SaveRecordMapper;
import com.lixxy.depositplan.model.SaveRecordBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SavedRecordService {

    @Autowired
    SaveRecordMapper recordMapper;


    /**
     * @param planId  planId
     * @param isSaved 0是未存 1是存了 -1是不限制
     */
    public List<SaveRecordBean> getSaveRecords(int planId, int isSaved) {
        return recordMapper.selectSaveRecords(planId, isSaved);
    }

    public Long getSavedMoney(int planId) {
        List<SaveRecordBean> saveRecords = getSaveRecords(planId, 1);
        Long total = 0L;
        for (SaveRecordBean saveRecordBean : saveRecords) {
            total += saveRecordBean.getMoney();
        }
        return total;
    }

    //存钱
    public Integer saveMoney(int id) {
        return recordMapper.saveMoney(id);
    }

    public int insertSelective(SaveRecordBean record) {
        return recordMapper.insertSelective(record);
    }

    public SaveRecordBean selectTodayRecordByPlanId(Integer planId, Date date) {
        return recordMapper.selectTodayRecordByPlanId(planId,date);
    }

    public Integer getLastInsertId() {
        return recordMapper.getLastInsertId();
    }
}
