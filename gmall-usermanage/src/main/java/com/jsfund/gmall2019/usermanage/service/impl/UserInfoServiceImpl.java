package com.jsfund.gmall2019.usermanage.service.impl;

import com.jsfund.gmall2019.UserInfo;
import com.jsfund.gmall2019.usermanage.mapper.UserInfoMapper;
import com.jsfund.gmall2019.usermanage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>gmall2019</h3>
 * <p>用户类实现类</p>
 *
 * @author : jiajk
 * @date : 2019-11-24 12:34
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

 @Autowired
  private UserInfoMapper userInfoMapper;
    @Override
    public List<UserInfo> getUserInfoList() {
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        return userInfos;
    }
}
