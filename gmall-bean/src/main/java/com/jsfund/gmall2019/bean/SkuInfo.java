package com.jsfund.gmall2019.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 库存单元表
 */
@Data
public class SkuInfo implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//默认
    private String id;
    @Column
    private String spuId;
    @Column
    private String skuName;
    @Column
    private String skuDesc;
    @Column
    private String price;
    @Column
    private String weight;
    @Column
    private String tmId;
    @Column
    private String catalog3Id;
    @Column
    private String skuDefaultImg;
    @Transient
    private List<SkuImage> skuImageList;
    @Transient
    private List<SkuSaleAttrValue> skuSaleAttrValueList;
    @Transient
    private List<SkuAttrValue> skuAttrValueList;
}
