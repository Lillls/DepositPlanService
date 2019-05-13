package com.lixxy.depositplan.controller;

import com.lixxy.depositplan.model.PlanBean;
import com.lixxy.depositplan.model.SaveRecordBean;
import com.lixxy.depositplan.model.UserIdAndPlaId;
import com.lixxy.depositplan.service.PlanService;
import com.lixxy.depositplan.service.SavedRecordService;
import com.lixxy.depositplan.utils.DateUtils;
import com.lixxy.depositplan.utils.GenerateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/saveRecord")
public class SavedRecordController {

    @Autowired
    PlanService planService;

    @Autowired
    SavedRecordService recordService;

    @RequestMapping(value = "/uploadData")
    public @ResponseBody
    Map uploadData(@RequestBody Map<String, List<SaveRecordBean>> requestMap) {
        Map<String, Object> map = new HashMap<>();
        List<SaveRecordBean> recordBeanList = requestMap.get("list");
        int count = 0;
        for (SaveRecordBean saveRecordBean : recordBeanList) {
            saveRecordBean.setUserId(999);
            saveRecordBean.setPlanId(999);
            recordService.insertSelective(saveRecordBean);
            count++;
        }
        map.put("code", 100);
        map.put("msg", "数据上传成功");
        map.put("data", count);
        return map;
    }

    @RequestMapping(value = "/getSavedMoneyByPlanId")
    public @ResponseBody
    Map getSavedMoneyByPlanId(@RequestBody Map<String, Integer> requestMap) {
        Map<String, Object> map = new HashMap<>();
        Integer planId = requestMap.get("planId");
        Double saveRecord = recordService.getSavedMoney(planId);
        map.put("code", 100);
        map.put("msg", "success");
        map.put("data", saveRecord);
        return map;
    }

    @RequestMapping(value = "/saveMoney")
    public @ResponseBody
    Map saveMoney(@RequestBody Map<String, Integer> requestMap) {
        Map<String, Object> map = new HashMap<>();
        Integer id = requestMap.get("id");

        Integer integer = recordService.saveMoney(id);
        if (integer > 0) {
            map.put("code", 100);
            map.put("msg", "success");
        } else {
            map.put("code", -100);
            map.put("msg", "failure");
        }

        return map;
    }

    @RequestMapping(value = "/getTodayMoney")
    public @ResponseBody
    Map getTodayMoney(@RequestBody UserIdAndPlaId bean) {
        Map<String, Object> map = new HashMap<>();
        int planId = bean.getPlanId();
        Date date = bean.getDate();
        PlanBean planBean = planService.selectByPrimaryKey(planId);
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
                recordBean = getTodayRecord5(planId, planBean,date);
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

    /**
     * @param planId   planId
     * @param planBean 计划bean
     * @param date
     * @return 记录
     */
    private SaveRecordBean getTodayRecord5(int planId, PlanBean planBean, Date date) {
        if (date==null){
            date = new Date();
        }
        //查询今天是否有记录
        SaveRecordBean recordBean = recordService.selectTodayRecordByPlanId(planId,date);
        if (recordBean == null) {
            List<SaveRecordBean> saveRecordBeans =
                    recordService.getSaveRecords(planId, -1);
            int totalDays = DateUtils.getDistanceOfTwoDate(planBean.getCreateDate(), planBean.getEndDate());
            List<Long> list = GenerateUtils.generateList(planBean.getStartMoney(), planBean.getIncreaseMoney(), totalDays);

            for (SaveRecordBean saveRecordBean : saveRecordBeans) {
                list.remove(saveRecordBean.getMoney());
            }
            Random random = new Random();
            int randomInt = random.nextInt(list.size());

            recordBean = new SaveRecordBean();
            recordBean.setPlanId(planId);
            recordBean.setMoney(list.get(randomInt).doubleValue());
            recordBean.setCreateDate(date);
            int i = recordService.insertSelective(recordBean);
            Integer insertId = recordService.getLastInsertId();
            recordBean.setId(insertId);
            if (i == 0) {
                return null;
            }
        }
        recordBean.setMoney(recordBean.getMoney() / 100);
        return recordBean;
    }

}
