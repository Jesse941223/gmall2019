package com.jsfund.gmall2019.manage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jsfund.gmall2019.bean.BaseAttrInfo;
import com.jsfund.gmall2019.bean.SkuInfo;
import com.jsfund.gmall2019.bean.SpuImage;
import com.jsfund.gmall2019.bean.SpuSaleAttr;
import com.jsfund.gmall2019.usermanage.service.ManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class SkuManageController {
    @Reference
    private ManageService manageService;

    /**
     * 获取平平台销售属性
     * @param spuId
     * @return
     */
    @RequestMapping("/spuSaleAttrList")
    @ResponseBody
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId){
        return manageService.getSpuSaleAttrList(spuId);
    }


    /**
     * 获取图片列表
     * @param spuId
     * @return
     */
    @RequestMapping("/spuImageList")
    @ResponseBody
    public List<SpuImage> getSpuImageList(String spuId){
        return manageService.getSpuImageList(spuId);
    }
    /**
     * 根据catlog3Id 获取平台属性列表
     * @param catalog3Id
     * @return
     */
    @RequestMapping("/attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> attrInfoList( String catalog3Id){
        return manageService.getAttrInfoList(catalog3Id);
    }
    /**
     * 保存sku
     */
    @RequestMapping("/saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
        return "ok";
    }
}
