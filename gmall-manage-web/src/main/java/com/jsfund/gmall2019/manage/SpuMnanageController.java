package com.jsfund.gmall2019.manage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jsfund.gmall2019.bean.SpuInfo;
import com.jsfund.gmall2019.usermanage.service.ManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
