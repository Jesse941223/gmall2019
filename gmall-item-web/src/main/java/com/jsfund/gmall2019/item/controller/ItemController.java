package com.jsfund.gmall2019.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.jsfund.gmall2019.bean.SkuInfo;
import com.jsfund.gmall2019.bean.SkuSaleAttrValue;
import com.jsfund.gmall2019.bean.SpuSaleAttr;
import com.jsfund.gmall2019.usermanage.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin //解决跨域问题
public class ItemController {
    private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

    @Reference
    private ManageService manageService;

    //请求跳转到详情页
    @RequestMapping("{skuId}.html")
    public String skuInfoPage(@PathVariable(value = "skuId") String skuId, HttpServletRequest request) {
        LOG.info("商品ID:" + skuId);
        //通过skuId 查询商品信息
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        //获取销售属性值集合，以及skuId
        List<SkuSaleAttrValue> saleAttrValueListBySpu = manageService.getSkuSaleAttrValueListBySpu(skuInfo.getSpuId());
        //加工数据
//        118	49	黑色
//        120	49	v1.0
//        118	50	黑色
//        121	50	v2.0
        //组装成 map.put(117|118,49)
        //      map.put(117|118,49)
        //声明一个key
        String key = "";
        //声明一个map
        Map<String, Object> hashMap = new HashMap<>();
        for (int i = 0; i < saleAttrValueListBySpu.size(); i++) {
            SkuSaleAttrValue skuSaleAttrValue = saleAttrValueListBySpu.get(i);
            if (key.length() > 0) {
                key += "|";
            }
            key += skuSaleAttrValue.getSaleAttrValueId();
            //当循环的skuId和下次循环的skuId不相等时，
            //当循环的下标到最最后的时候，循环结束，将数据添加到map中
            if (saleAttrValueListBySpu.size() == (i+1) || !skuSaleAttrValue.getSkuId().equals(saleAttrValueListBySpu.get(i + 1).getSkuId())) {
                hashMap.put(key, skuSaleAttrValue.getSkuId());
                //将key 置空
                key = "";
            }
        }
        //将map 转换成json 然后保存到域中
        String svaluesSkuJson = JSON.toJSONString(hashMap);
        LOG.info("传输数据："+svaluesSkuJson);
        request.setAttribute("svaluesSkuJson", svaluesSkuJson);
        //保存skuInfo
        request.setAttribute("skuInfo", skuInfo);
        //调用服务层，获取销售属性数据集合
        List<SpuSaleAttr> saleAttrList = manageService.selectSpuSaleAttrListCheckBySku(skuInfo);
        request.setAttribute("saleAttrList", saleAttrList);
        return "item";
    }
}
