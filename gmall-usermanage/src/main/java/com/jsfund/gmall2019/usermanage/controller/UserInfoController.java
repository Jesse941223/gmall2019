package com.jsfund.gmall2019.usermanage.controller;

import com.jsfund.gmall2019.UserInfo;
import com.jsfund.gmall2019.usermanage.service.UserInfoService;
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
        return userInfoList;
    }


}
