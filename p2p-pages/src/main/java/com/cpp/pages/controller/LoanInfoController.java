package com.cpp.pages.controller;


import com.cpp.pages.pojo.BidInfo;
import com.cpp.pages.pojo.LoanInfo;
import com.cpp.pages.service.BidInfoService;
import com.cpp.pages.service.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class LoanInfoController {

    @Autowired
    LoanInfoService loanInfoService;
    @Resource
    BidInfoService bidInfoService;

    @RequestMapping("/loan/loanInfo")
    public String loanInfo(
            @RequestParam(value = "productId",required = true) Integer productId,
            Model model
    ){
        LoanInfo loanInfo = loanInfoService.queryProductById(productId);
        model.addAttribute("loanInfo",loanInfo);
        List<BidInfo> bidInfos = bidInfoService.queryConsumerByProductId(productId);
        model.addAttribute("bidInfoList",bidInfos);

        return "loaninfo";
    }
}
