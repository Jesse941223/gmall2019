package com.jsfund.gmall2019.usermanage.service.impl;

import com.jsfund.gmall2019.bean.UserAddress;
import com.jsfund.gmall2019.usermanage.mapper.UserAddressMapper;
import com.jsfund.gmall2019.usermanage.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @ClassName UserAddressServiceImpl
 * @Author jiajk
 * @Date 2019/11/24 20:12
 **/
@Service
public class UserAddressServiceImpl implements UserAddressService{
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> userAddresses = userAddressMapper.select(userAddress);
        if (userAddresses!=null&&userAddresses.size()>0) {
            return userAddresses;

        }
        return null;
    }
}
