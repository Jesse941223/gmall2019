package com.jsfund.gmall2019.manage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jsfund.gmall2019.bean.*;
import com.jsfund.gmall2019.manage.mapper.*;
import com.jsfund.gmall2019.usermanage.service.ManageService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Service
public class manageServiceImpl implements ManageService {
    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    @Autowired
    private SpuInfoMapper spuInfoMapper;

    /**
     * 获取一级分类列表
     *
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
    public List<BaseCatalog3> getCatalog3(String catlog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catlog2Id);
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

    /**
     * 保存平台属性信息
     *
     * @param baseAttrInfo
     */
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        // 操作BaseAttrInfo
        if (baseAttrInfo.getId()!=null && baseAttrInfo.getId().length()>0){
            // 做修改
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else{
            // 对应调用mapper对象进行新增
            baseAttrInfo.setId(null); // 使id 实现自增
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }

        // 对属性值的操作BaseAttrValue
        // 无论是新增，还是修改，先将BaseAttrValue 中 的数据删除 {baseAttrValue.attrId==baseAttrInfo.id}
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue);

        // 添加数据BaseAttrValue
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList!=null && attrValueList.size()>0){
            // 循环遍历
            for (BaseAttrValue attrValue : attrValueList) {
                attrValue.setId(null);
                attrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(attrValue);
            }
        }
    }

    /**
     * 通过属性id 查询属性集合
     * @param attrId
     * @return
     */
    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        if (attrId != null && attrId.length() > 0) {
            BaseAttrValue baseAttrValue = new BaseAttrValue();
            baseAttrValue.setAttrId(attrId);
            List<BaseAttrValue> baseAttrValues = baseAttrValueMapper.select(baseAttrValue);
            if (CollectionUtils.isNotEmpty(baseAttrValues)) {
                return baseAttrValues;
            }
            return Collections.emptyList();
        }
        return null;
    }

    /**
     * 根据属性id 查平台属性
     * @param attrId
     * @return
     */
    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);
        //为平台属性值列表赋值
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        List<BaseAttrValue> baseAttrValues = baseAttrValueMapper.select(baseAttrValue);
        // 给平台属性值集合赋值
        baseAttrInfo.setAttrValueList(baseAttrValues);
        return baseAttrInfo;
    }

    /**
     * 通过分类id查询商品集合
     * @param catalog3Id
     * @return
     */
    @Override
    public List<SpuInfo> getSpuList(String catalog3Id) {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        List<SpuInfo> spuInfoList = spuInfoMapper.select(spuInfo);
        if (CollectionUtils.isNotEmpty(spuInfoList)) {
            return spuInfoList;
        }
        return Collections.emptyList();
    }
}
