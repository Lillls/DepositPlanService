package com.lixxy.depositplan.controller;

import com.lixxy.depositplan.dao.SaveRecordMapper;
import com.lixxy.depositplan.model.SaveRecordBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    SaveRecordMapper saveRecordDao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    @RequestMapping(value = "/test")
    public @ResponseBody
    Map<String, Object> test(@RequestBody Map<String, Object> map) {
        map.put("data", "hello world");
        return map;
    }
}
