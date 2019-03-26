package com.lixxy.depositplan.controller;

import com.lixxy.depositplan.dao.PlanMapper;
import com.lixxy.depositplan.dao.SaveRecordMapper;
import com.lixxy.depositplan.model.PlanBean;
import com.lixxy.depositplan.model.SaveRecordBean;
import com.lixxy.depositplan.model.UserIdAndPlaId;
import com.lixxy.depositplan.utils.DateUtils;
import com.lixxy.depositplan.utils.GenerateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/saveRecord")
public class SavedRecordController {

    @Autowired
    SaveRecordMapper saveRecordDao;
    @Autowired
    PlanMapper planDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    @RequestMapping(value = "/uploadData")
    public @ResponseBody
    Map uploadData(@RequestBody Map<String, List<SaveRecordBean>> requestMap) {
        Map<String, Object> map = new HashMap<>();
        List<SaveRecordBean> recordBeanList = requestMap.get("list");
        int count = 0;
        for (SaveRecordBean saveRecordBean : recordBeanList) {
            saveRecordBean.setUserId(999);
            saveRecordBean.setPlanId(999);
            saveRecordDao.insert(saveRecordBean);
            count++;
        }
        map.put("code", 100);
        map.put("msg", "数据上传成功");
        map.put("data", count);
        return map;
    }

    @RequestMapping(value = "/getSavedMoneyByPlanId")
    public @ResponseBody
    Map getSavedMoneyByPlanId(@RequestBody UserIdAndPlaId bean) {
        Map<String, Object> map = new HashMap<>();
        List<SaveRecordBean> saveRecordBeans =
                saveRecordDao.selectSaveRecordByUserIdAndPlanId(bean.getUserId(), bean.getPlanId());
        int total = 0;
        for (SaveRecordBean saveRecordBean : saveRecordBeans) {
            total += saveRecordBean.getMoney();
        }
        map.put("code", 100);
        map.put("msg", "success");
        map.put("data", total / 100);
        return map;
    }

    @RequestMapping(value = "/getTodayMoney")
    public @ResponseBody
    Map getTodayMoney(@RequestBody UserIdAndPlaId bean) {
        Map<String, Object> map = new HashMap<>();
        int userId = bean.getUserId();
        int planId = bean.getPlanId();
        PlanBean planBean = planDao.selectByPrimaryKey(planId);
        SaveRecordBean recordBean = null;
        switch (planBean.getType()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:

                break;
            case 5:
                recordBean = getTodayRecord5(userId, planId, planBean);
                break;
            default:
                break;
        }

        if (recordBean == null) {
            map.put("code", -100);
        } else {
            map.put("code", 100);
            map.put("data", recordBean);
        }
        return map;
    }

    private SaveRecordBean getTodayRecord5(int userId, int planId, PlanBean planBean) {
        //查询今天是否有记录
        SaveRecordBean recordBean = saveRecordDao.selectTodayRecordByPlanId(userId);
        if (recordBean == null) {
            List<SaveRecordBean> saveRecordBeans =
                    saveRecordDao.selectSaveRecordByUserIdAndPlanId(userId, planId);
            int totalDays = DateUtils.getDistanceOfTwoDate(planBean.getCreateDate(), planBean.getEndDate());
            List<Long> list = GenerateUtils.generateList(planBean.getStartMoney(), planBean.getIncreaseMoney(), totalDays);

            for (SaveRecordBean saveRecordBean : saveRecordBeans) {
                list.remove(saveRecordBean.getMoney());
            }
            Random random = new Random();
            int randomInt = random.nextInt(list.size());

            recordBean = new SaveRecordBean();
            recordBean.setPlanId(planId);
            recordBean.setUserId(userId);
            recordBean.setMoney(list.get(randomInt));
            int i = saveRecordDao.insertSelective(recordBean);
            Integer insertId = saveRecordDao.getLastInsertId();
            recordBean.setId(insertId);
            if (i == 0) {
                return null;
            }
        }
        return recordBean;
    }

}
