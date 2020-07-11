package com.jsfund.gmall2019.usermanage.service;

import com.jsfund.gmall2019.bean.BaseAttrInfo;
import com.jsfund.gmall2019.bean.BaseCatalog1;
import com.jsfund.gmall2019.bean.BaseCatalog2;
import com.jsfund.gmall2019.bean.BaseCatalog3;

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
    public List<BaseAttrInfo> getAttrList(String catalog3Id);
}
