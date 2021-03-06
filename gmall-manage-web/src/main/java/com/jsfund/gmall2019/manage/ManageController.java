package com.jsfund.gmall2019.manage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jsfund.gmall2019.bean.*;
import com.jsfund.gmall2019.config.LoginRequire;
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
public class ManageController {
    @Reference
    private ManageService manageService;

    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }

    /**
     * 查询一级分类列表
     */
    @RequestMapping("/getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getCatlog1(){
        List<BaseCatalog1> catalog1s = manageService.getCatalog1();
        return catalog1s;
    }
    /**
     * 根据catlog1Id 查询二级分类列表
     */
    @RequestMapping("/getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
        return manageService.getCatalog2(catalog1Id);
    }
    /**
     * 根据catlog2Id 查询三级分类列表
     */
    @RequestMapping("/getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(@Param("catlog2Id") String catalog2Id){
        return manageService.getCatalog3(catalog2Id);
    }
    /**
     * 通过属性id 查询属性值列表
     */
    @RequestMapping("/getAttrValueList")
    @ResponseBody
    public List<BaseAttrValue> getAttrValueList (String attrId){
        //return manageService.getAttrValueList( attrId);
        BaseAttrInfo baseAttrInfo = manageService.getAttrInfo(attrId);
        return baseAttrInfo.getAttrValueList();
    }
    /**
     * 保存平台属行，平台属性值
     */
    @RequestMapping("/saveAttrInfo")
    @ResponseBody
    //@LoginRequire(autoRedirect = true)
    public void saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
    }


}
