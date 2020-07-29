package com.jsfund.gmall2019.manage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.jsfund.gmall2019.bean.*;
import com.jsfund.gmall2019.config.RedisUtil;
import com.jsfund.gmall2019.manage.constant.ManageConst;
import com.jsfund.gmall2019.manage.mapper.*;
import com.jsfund.gmall2019.usermanage.service.ManageService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;

@Service
public class manageServiceImpl implements ManageService {
    private static final Logger LOG = LoggerFactory.getLogger(manageServiceImpl.class);
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
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuSaleAtrrValueMapper skuSaleAtrrValueMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private RedisUtil redisUtil;

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

    /**
     * 通过三级分类id 查询平台属性，属性值集合
     *
     * @param catlog3Id 改造后的方法实现
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrInfoList(String catlog3Id) {
        return baseAttrInfoMapper.getBaseAttrInfoListByCatalog3Id(Long.parseLong(catlog3Id));
    }

    /**
     * 保存平台属性信息
     *
     * @param baseAttrInfo
     */
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        // 操作BaseAttrInfo
        if (baseAttrInfo.getId() != null && baseAttrInfo.getId().length() > 0) {
            // 做修改
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        } else {
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
        if (attrValueList != null && attrValueList.size() > 0) {
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
     *
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
     *
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
     *
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

    @Override
    public List<BaseSaleAttr> getSaleAttrList() {
        LOG.info("查询所有销售属性集合信息，开始查询");
        List<BaseSaleAttr> baseSaleAttrs = baseSaleAttrMapper.selectAll();
        if (CollectionUtils.isNotEmpty(baseSaleAttrs)) {
            return baseSaleAttrs;
        }
        //如果没有值，返回空集合
        return Collections.emptyList();
    }

    /**
     * 保存商品
     *
     * @param spuInfo
     */
    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        if (spuInfo.getId() == null || spuInfo.getId().length() == 0) {
            //保存数据
            spuInfo.setId(null);
            spuInfoMapper.insertSelective(spuInfo);
        } else {
            //更新操作
            spuInfoMapper.updateByPrimaryKeySelective(spuInfo);
        }
        // 保存图片前先删除图片
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuInfo.getId());
        spuImageMapper.delete(spuImage);
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (spuImageList != null && spuImageList.size() > 0) {
            for (SpuImage image : spuImageList) {
                image.setId(null);
                image.setSpuId(spuInfo.getId());
                spuImageMapper.insertSelective(image);
            }
        }
        //获取数据
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (spuSaleAttrList != null && spuSaleAttrList.size() > 0) {
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setId(null);
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insertSelective(spuSaleAttr);

                //添加平台属性值
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                if (spuSaleAttrValueList != null && spuSaleAttrValueList.size() > 0) {
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        spuSaleAttrValue.setId(null);
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
                    }
                }
            }
        }


    }

    /**
     * 查询商品销售属性集合
     * 通过改造，关联外表进行多表查询
     *
     * @param spuId
     * @return
     */

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId) {
        List<SpuSaleAttr> spuSaleAttrs = spuSaleAttrMapper.selectSpuSaleAttrList(Long.parseLong(spuId));
        if (CollectionUtils.isNotEmpty(spuSaleAttrs)) {
            return spuSaleAttrs;
        }
        return Collections.emptyList();
    }

    /**
     * 通过商品id 获取图片集合
     *
     * @param spuId
     * @return
     */
    @Override
    public List<SpuImage> getSpuImageList(String spuId) {
        if (spuId != null && spuId.length() > 0) {
            SpuImage spuImage = new SpuImage();
            spuImage.setSpuId(spuId);
            List<SpuImage> spuImages = spuImageMapper.select(spuImage);
            if (CollectionUtils.isNotEmpty(spuImages)) {
                return spuImages;
            }
            // 如果返回空集合
            return Collections.emptyList();
        }
        return null;
    }

    /**
     * 保存sku信息数据
     *
     * @param skuInfo
     */
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //保存skuInfo信息
        if (skuInfo.getId() == null || skuInfo.getId().length() == 0) {
            skuInfoMapper.insertSelective(skuInfo);
        } else {
            skuInfoMapper.updateByPrimaryKeySelective(skuInfo);
        }
        //保存前先删除数据
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuInfo.getId());
        skuImageMapper.delete(skuImage);

        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if (null != skuImageList && skuImageList.size() > 0) {
            for (SkuImage image : skuImageList) {
                image.setId(null);
                image.setSkuId(skuInfo.getId());
                //保存sku 图片
                skuImageMapper.insertSelective(image);
            }
        }
        //保存平台属性
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if (skuAttrValueList != null && skuAttrValueList.size() > 0) {
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setId(null);
                skuAttrValue.setSkuId(skuInfo.getId());
                //保存平台属性的关联
                skuAttrValueMapper.insertSelective(skuAttrValue);
            }
        }
        //保存销售属性值
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (null != skuSaleAttrValueList && skuSaleAttrValueList.size() > 0) {
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setId(null);
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                skuSaleAtrrValueMapper.insertSelective(skuSaleAttrValue);
            }
        }


    }

    /**
     * 通过skuId 查询商品信息
     */
    @Override
    public SkuInfo getSkuInfo(String skuId) {
        //测试一下Redis是否可用
//        Jedis jedis = redisUtil.getJedis();
//        jedis.set("k1","就是不会！");
//        jedis.close();
        Jedis jedis = redisUtil.getJedis();
        try {
            //声明存储的商品的key
            String skuKey = ManageConst.SKUKEY_PREFIX + skuId + ManageConst.SKUKEY_SUFFIX;
            if (jedis.exists(skuKey)) {
                //key存在，则取出key中的数据
                String skuJson = jedis.get(skuKey);
                if (skuJson != null && skuJson.length() > 0) {
                    //将json转换成skuInfo 对象
                    SkuInfo skuInfo = JSON.parseObject(skuJson, SkuInfo.class);
                    return skuInfo;
                }
            } else {
                //如果不存在，从 数据库中取数据
                SkuInfo skuInfoDB = getSkuInfoDB(skuId);
                //将数据放入redis中
                // jedis.set(skuKey,JSON.toJSONString(skuInfoDB));
                //存入到redis中，设置过期时间的24*60*60
                jedis.setex(skuKey,ManageConst.SKUKEY_TIMEOUT,JSON.toJSONString(skuInfoDB));
                return skuInfoDB;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jedis.close();
        //如果有错误代码块直接通过skuId 去取
        return getSkuInfoDB(skuId);
    }
    /**
     * 从数据库中获取数据
     * @param skuId
     * @return
     */
    private SkuInfo getSkuInfoDB(String skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuId);
        //通过skuid 查询skuImageList 然后赋值
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuId);
        List<SkuImage> skuImages = skuImageMapper.select(skuImage);
        skuInfo.setSkuImageList(skuImages);
        return skuInfo;
    }

    /**
     * 通过skuId 查询spu 的销售属性
     * @return
     */
    @Override
    public List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(SkuInfo skuInfo) {
        return spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(skuInfo.getId(), skuInfo.getSpuId());

    }

    /**
     *  通过spuId 查询skuSaleAttrValue的集合
     * @param spuId
     * @return
     */
    @Override
    public List<SkuSaleAttrValue> getSkuSaleAttrValueListBySpu(String spuId) {

        return skuSaleAtrrValueMapper.getSkuSaleAttrValueListBySpu(spuId);
    }

}
