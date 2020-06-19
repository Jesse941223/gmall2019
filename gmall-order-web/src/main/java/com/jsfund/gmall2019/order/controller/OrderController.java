package com.jsfund.gmall2019.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jsfund.gmall2019.bean.UserAddress;
import com.jsfund.gmall2019.usermanage.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @version 1.0
 * @ClassName OrderController
 * @Author jiajk
 * @Date 2019/11/24 19:59
 **/
@RestController
public class OrderController {
    //@Autowired
    @Reference
    private UserAddressService userAddressService;
    @RequestMapping("trad")
    public List<UserAddress> trad(HttpServletRequest request){
        String userId = request.getParameter("userId");
        List<UserAddress> userAddressList = userAddressService.getUserAddressList(userId);
        return userAddressList;

    }



}
