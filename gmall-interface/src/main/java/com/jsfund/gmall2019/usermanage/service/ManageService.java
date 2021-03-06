package com.jsfund.gmall2019.usermanage.service;

import com.jsfund.gmall2019.bean.*;

import java.util.List;

/**
 * 后台管理接口
 */
public interface ManageService {
    /**
     * 获取catlog1 一级分类列表
     */
    public List<BaseCatalog1> getCatalog1();
    /**
     * 通过catlog1Id 获取catlog2列表 二级分类列表
     */
    public List<BaseCatalog2> getCatalog2(String catalog1Id);
    /**
     * 通过catlog3Id 获取三级分类列表
     */
    public List<BaseCatalog3> getCatalog3(String catalog2id);

    /**
     * 通过catlog3Id获取平台属性列表
     */
    public List<BaseAttrInfo> getAttrInfoList(String catalog3Id);

    /**
     * 保存平台属性，属性值
     * @param baseAttrInfo
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    List<BaseAttrValue> getAttrValueList(String attrId);

    /**
     * 通过属性id查询平台属性
     * @param attrId
     * @return
     */

    BaseAttrInfo getAttrInfo(String attrId);

    /**
     * 通过分类id查询商品列表
     * @param cataLog3Id
     * @return
     */
    List<SpuInfo> getSpuList(String cataLog3Id);
    /**
     * 查询所有的销售属性列表
     *
     */
    List<BaseSaleAttr> getSaleAttrList();

    /**
     *保存商品
     * @param spuInfo
     */

    void saveSpuInfo(SpuInfo spuInfo);

    List<SpuSaleAttr> getSpuSaleAttrList(String spuId);

    List<SpuImage> getSpuImageList(String spuId);

    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 通过skuId 查询商品信息
     * @param skuId
     * @return
     */
    SkuInfo getSkuInfo(String skuId);

    /**
     * 通过skuId 查询spu的销售属性
     * @param skuInfo
     * @return
     */

    List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(SkuInfo skuInfo);

    /**
     * 通过spuId 的到skuSaleAttrValue集合
     * @param spuId
     * @return
     */
     List<SkuSaleAttrValue> getSkuSaleAttrValueListBySpu(String spuId);
}
