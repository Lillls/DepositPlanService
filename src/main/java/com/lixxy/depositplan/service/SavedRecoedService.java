package com.lixxy.depositplan.service;

import com.lixxy.depositplan.dao.SaveRecordMapper;
import com.lixxy.depositplan.model.SaveRecordBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedRecoedService {
    @Autowired
    SaveRecordMapper saveRecordDao;


    /**
     *
     * @param planId planId
     * @param isSaved 0是未存 1是存了 -1是不限制
     */
    public List<SaveRecordBean> getSaveRecords(int planId, int isSaved) {
        return saveRecordDao.selectSaveRecords(planId, isSaved);
    }

    public Double getSavedMoney(int planId) {
        List<SaveRecordBean> saveRecords = getSaveRecords(planId, 1);
        Long total = 0L;
        for (SaveRecordBean saveRecordBean : saveRecords) {
            total += saveRecordBean.getMoney();
        }
        return total / 100.0;
    }

    //存钱
    public Integer saveMoney(int id){
      return  saveRecordDao.saveMoney(id);
    }
}
