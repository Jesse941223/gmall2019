package com.jsfund.gmall2019.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageController {

    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }
}
