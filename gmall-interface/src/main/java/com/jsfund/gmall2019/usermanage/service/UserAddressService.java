package com.jsfund.gmall2019.usermanage.service;

import com.jsfund.gmall2019.bean.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> getUserAddressList(String userId);
}
