package com.cpp.pages.controller;


import com.cpp.common.Constants;
import com.cpp.jwt.utils.JwtTokenUtil;
import com.cpp.pages.pojo.BidInfo;
import com.cpp.pages.pojo.LoanInfo;
import com.cpp.pages.pojo.User;
import com.cpp.pages.service.BidInfoService;
import com.cpp.pages.service.LoanInfoService;
import com.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class LoanInfoController {

    @Autowired
    LoanInfoService loanInfoService;
    @Resource
    BidInfoService bidInfoService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping("/loan/loanInfo")
    public String loanInfo(
            @RequestParam(value = "productId",required = true) Integer productId,
            Model model,
            HttpServletRequest request
    ){
        LoanInfo loanInfo = loanInfoService.queryProductById(productId);
        model.addAttribute("loanInfo",loanInfo);
        List<BidInfo> bidInfos = bidInfoService.queryConsumerByProductId(productId);
        model.addAttribute("bidInfoList",bidInfos);

        String token = ServletUtils.getToken(request);
        if (token != null){
            String phone = jwtTokenUtil.getUserIdFromToken(token);
            String username = jwtTokenUtil.getUserNameFromToken(token);
            if(phone != null){
                User user = new User();
                user.setPhone(phone);
                user.setName(username);
                if (request.getSession().getAttribute(Constants.LOGIN_USER_INFO) == null){
                    request.getSession().setAttribute(Constants.LOGIN_USER_INFO, user);
                }

            }
        }else {
            request.getSession().removeAttribute(Constants.LOGIN_USER_INFO);
        }

        return "loaninfo";
    }
}
