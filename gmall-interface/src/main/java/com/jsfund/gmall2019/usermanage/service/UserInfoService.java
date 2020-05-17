package com.jsfund.gmall2019.usermanage.service;

import com.jsfund.gmall2019.bean.UserInfo;

import java.util.List;

public interface UserInfoService {
     // 获取所有用户信息
    List<UserInfo> getUserInfoList();

    // 添加用户
    void addUser(UserInfo userInfo);

    // 修改用户
    void updateUser(UserInfo userinfo);

    //通过id 查询用户信息

    UserInfo selectUserById(String id);

}
