package com.jsfund.gmall2019.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 销售属性字典表
 */
@Data
public class BaseSaleAttr implements Serializable {

    @Column
    @Id
    private String id;
    @Column
    private String name;



}
