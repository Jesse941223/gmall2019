package com.jsfund.gmall2019.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * sku销售属性表
 */
@Data
public class SkuSaleAttrValue implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String skuId;
    @Column
    private String saleAttrId;
    /**
     * 销售属性值ld
     */
    @Column
    private String saleAttrValueId;
    @Column
    private String saleAttrName;
    @Column
    private String saleAttrValueName;
}
