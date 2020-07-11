package com.jsfund.gmall2019.manage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jsfund.gmall2019.bean.BaseAttrInfo;
import com.jsfund.gmall2019.bean.BaseCatalog1;
import com.jsfund.gmall2019.bean.BaseCatalog2;
import com.jsfund.gmall2019.bean.BaseCatalog3;
import com.jsfund.gmall2019.manage.mapper.BaseAttrInfoMapper;
import com.jsfund.gmall2019.manage.mapper.BaseCatalog1Mapper;
import com.jsfund.gmall2019.manage.mapper.BaseCatalog2Mapper;
import com.jsfund.gmall2019.manage.mapper.BaseCatalog3Mapper;
import com.jsfund.gmall2019.usermanage.service.ManageService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
@Service
public class manageServiceImpl implements ManageService{
    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    /**
     * 获取一级分类列表
     * @return
     */
    @Override
    public List<BaseCatalog1> getCatalog1() {
        List<BaseCatalog1> baseCatalog1s = baseCatalog1Mapper.selectAll();
        if (CollectionUtils.isNotEmpty(baseCatalog1s)) {
            return baseCatalog1s;
        }
        //返回空集合
        return Collections.emptyList();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catlog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catlog1Id);
        List<BaseCatalog2> baseCatalog2s = baseCatalog2Mapper.select(baseCatalog2);
        if (CollectionUtils.isNotEmpty(baseCatalog2s)) {
            return baseCatalog2s;
        }
        //没有返回空集合
        return Collections.emptyList();
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catlog2id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catlog2id);
        List<BaseCatalog3> baseCatalog3s = baseCatalog3Mapper.select(baseCatalog3);
        if (CollectionUtils.isNotEmpty(baseCatalog3s)) {
            return baseCatalog3s;
        }
        return Collections.emptyList();
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catlog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catlog3Id);
        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.select(baseAttrInfo);
        if (CollectionUtils.isEmpty(baseAttrInfos)) {
            //如果不存在返回空集合
            return Collections.emptyList();
        }
        return baseAttrInfos;
    }
}
