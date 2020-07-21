package com.jsfund.gmall2019.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * sku平台属性关联表
 */
@Data
public class SkuAttrValue implements Serializable {
    @Column
    @Id
    private String id;
    @Column
    private String attrId;
    @Column
    private String valueId;
    @Column
    private String skuId;

}
