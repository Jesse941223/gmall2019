package com.jsfund.gmall2019.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 平台属性表
 */
@Data
public class BaseAttrInfo implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String attrName;
    /**
     * 三级分类id
     */
    @Column
    private String catalog3Id;
    /**
     * 平台属性值列表
     */
    @Transient
    private List<BaseAttrValue> attrValueList;

}
