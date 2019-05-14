package com.lixxy.depositplan.controller;

import com.lixxy.depositplan.model.PlanBean;
import com.lixxy.depositplan.model.PlanDetailBean;
import com.lixxy.depositplan.service.PlanService;
import com.lixxy.depositplan.service.SavedRecordService;
import com.lixxy.depositplan.utils.DateUtils;

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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/plan")
public class PlanController {

    @Autowired
    PlanService planService;
    @Autowired
    SavedRecordService recordService;

    @RequestMapping(value = "/uploadData")
    public @ResponseBody
    Map uploadData(@RequestBody Map<String, List<PlanBean>> requestMap) {
        Map<String, Object> map = new HashMap<>();
        List<PlanBean> planBeanList = requestMap.get("list");
        int count = 0;
        for (PlanBean planBean : planBeanList) {
            planService.insertSelective(planBean);
            planBean.setUserId(999);
            count++;
        }
        map.put("code", 100);
        map.put("msg", "数据上传成功");
        map.put("data", count);
        return map;
    }

    @RequestMapping(value = "/getPlanById")
    public @ResponseBody
    Map getPlanById(@RequestBody Map<String, Integer> requestMap) {
        Map<String, Object> map = new HashMap<>();
        Integer planId = requestMap.get("planId");
        PlanBean planBean = planService.selectByPrimaryKey(planId);

        map.put("code", 100);
        map.put("msg", "success");
        map.put("data", planBean);
        return map;
    }

    @RequestMapping(value = "/getPlanByUserId")
    public @ResponseBody
    Map getPlanByUserId(@RequestBody Map<String, Integer> requestMap) {
        Map<String, Object> map = new HashMap<>();
        Integer userId = requestMap.get("userId");
        List<PlanBean> planBeans = planService.selectByUserId(userId);
        map.put("code", 100);
        map.put("msg", "success");
        map.put("data", planBeans);
        return map;
    }

    @RequestMapping(value = "/getPlanDetail")
    public @ResponseBody
    Map getPlanDetail(@RequestBody Map<String, Integer> requestMap) {
        Map<String, Object> map = new HashMap<>();
        PlanDetailBean detailBean = new PlanDetailBean();
        Integer planId = requestMap.get("planId");
        PlanBean planBean = planService.selectByPrimaryKey(planId);
        Long savedMoney = recordService.getSavedMoney(planId);

        detailBean.setId(planId);
        detailBean.setName(planBean.getName());
        detailBean.setPassDays(DateUtils.getDistanceOfTwoDate(planBean.getCreateDate(), new Date()) + 1);
        detailBean.setTotalDays(DateUtils.getDistanceOfTwoDate(planBean.getCreateDate(), planBean.getEndDate()) + 1);
        detailBean.setTotalMoney(planBean.getMoney());
        detailBean.setSavedMoney(savedMoney);

        map.put("code", 100);
        map.put("msg", "success");
        map.put("data", detailBean);
        return map;
    }


}
