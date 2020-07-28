package com.jsfund.gmall2019.manage.mapper;

import com.jsfund.gmall2019.bean.SkuSaleAttrValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SkuSaleAtrrValueMapper extends Mapper<SkuSaleAttrValue> {
    /**
     * 通过spuId 查询sku的属性值集合
     * @param spuId
     * @return
     */
    List<SkuSaleAttrValue> getSkuSaleAttrValueListBySpu(String spuId);
}
