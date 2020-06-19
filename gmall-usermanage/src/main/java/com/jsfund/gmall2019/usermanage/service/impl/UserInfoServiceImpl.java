package com.jsfund.gmall2019.usermanage.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.jsfund.gmall2019.bean.UserInfo;
import com.jsfund.gmall2019.usermanage.mapper.UserInfoMapper;
import com.jsfund.gmall2019.usermanage.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

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

    /**
     * 添加用户
     * @param userInfo
     */

    @Override
    public void addUser(UserInfo userInfo) {
        // 调用mapper
        userInfoMapper.insert(userInfo);

    }

    /**
     * 更新用户信息
     * @param userInfo
     */

    @Override
    public void updateUser(UserInfo userInfo) {
        // 判断用户是否存在
        if (userInfo.getId()!= null){
            UserInfo selectOne = userInfoMapper.selectOne(userInfo);
            if (Objects.nonNull(selectOne)) {
                // 更新用户信息
                userInfoMapper.updateByPrimaryKeySelective(userInfo);
            }else {
                // 插入一条新的记录
                userInfo.setId(null);
                userInfoMapper.insert(userInfo);
            }
        }


    }

    /**
     *  通过用户查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserInfo selectUserById(String id) {
        if (null!=id) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(id);
            try{
                UserInfo selectOne = userInfoMapper.selectOne(userInfo);
            }catch (Exception e){
                e.printStackTrace();

            }

        }
        return null;
    }

    /**
     *  模糊查询
     * @return
     */
    @Override
    public List<UserInfo> getUserinfoListByLike() {
        Example example = new Example(UserInfo.class);
         example.createCriteria().andLike("loginName", "%a%");
         //查询登录名称含a的所有用户信息
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        return userInfos;
    }
}
