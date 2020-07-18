package com.jsfund.gmall2019.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 商品表实体类
 */
@Data
public class SpuInfo implements Serializable{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Mysql 默认
    private String id;
    @Column
    private String spuName;
    @Column
    private String description;
    @Column
    private String catalog3Id;
    //添加页面中的其他对象 销售属性 商品图片 List
    @Transient
    private List<SpuImage> spuImageList;
    @Transient
    private List<SpuSaleAttr> spuSaleAttrList;

}
