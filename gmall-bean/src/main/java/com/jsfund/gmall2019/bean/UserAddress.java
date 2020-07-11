package com.jsfund.gmall2019.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 1.0
 * @ClassName UserAddress 用户地址
 * @Author jiajk
 * @Date 2019/11/24 20:02
 **/
@Data
public class UserAddress  implements Serializable{
    @Column
    @Id
    private String id;
    @Column
    private String userAddress;
    @Column
    private String userId;
    @Column
    private String consignee;
    @Column
    private String phoneNum;
    @Column
    private String isDefault;

}
