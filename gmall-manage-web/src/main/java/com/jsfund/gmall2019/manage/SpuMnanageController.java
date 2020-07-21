package com.jsfund.gmall2019.manage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jsfund.gmall2019.bean.*;
import com.jsfund.gmall2019.usermanage.service.ManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin //跨域
public class SpuMnanageController {
    @Reference
    private ManageService manageService;

    /**
     * 通过三级分类查询商品列表
     * jiajk 20200717
     */
    @RequestMapping("/spuList")
    @ResponseBody
    public List<SpuInfo> getSpuList(@Param(value = "catalog3Id") String catalog3Id){
        return  manageService.getSpuList(catalog3Id);
    }
    /**
     * 查询商品的销售属性
     * @return
     */
    @RequestMapping("/baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> getBaseSaleAttrList(){
        return manageService.getSaleAttrList();
    }
    /**
     * 保存商品spu
     */
    @RequestMapping(value = "/saveSpuInfo",method = RequestMethod.POST)
    @ResponseBody
    public String saveSpuInfo(@RequestBody SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
        return "ok";

    }

}
