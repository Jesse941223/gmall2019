package com.jsfund.gmall2019.usermanage.controller;

import com.jsfund.gmall2019.bean.UserInfo;
import com.jsfund.gmall2019.usermanage.service.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <h3>gmall2019</h3>
 * <p>控制器</p>
 *
 * @author : jiajk
 * @date : 2019-11-24 13:23
 **/
@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "findAll")
    @ResponseBody
    public List<UserInfo> findAll() {
        List<UserInfo> userInfoList = userInfoService.getUserInfoList();
        if (CollectionUtils.isNotEmpty(userInfoList)) {
            return userInfoList;
        }
        return null;
    }
    @RequestMapping("add")
    @ResponseBody
    public String add(){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("906154249@qq.com");
        userInfo.setLoginName("jesse941223");
        userInfo.setPasswd("66666");
        userInfoService.addUser(userInfo);
        return "ok";
    }
    @RequestMapping("update")
    @ResponseBody
    public String update(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId("5");
        userInfo.setName("JIaJk");
        userInfo.setNickName("班长");
        userInfo.setName("刘翔");
        userInfoService.updateUser(userInfo);
        return "ok";
    }

}
