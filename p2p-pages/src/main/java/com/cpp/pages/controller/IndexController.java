package com.cpp.pages.controller;

import com.cpp.common.Constants;
import com.cpp.jwt.utils.JwtTokenUtil;
import com.cpp.pages.pojo.LoanInfo;
import com.cpp.pages.pojo.User;
import com.cpp.pages.service.*;

import com.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    LoanInfoService loanInfoService;

    @Autowired
    UserService userService;

    @Autowired
    BidInfoService bidInfoService;

    @Autowired
    AccountService accountService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request, Model model){

        //查询历史收益率
        double avgRate = loanInfoService.queryAvgRate();
        request.setAttribute(Constants.AVG_RATE, avgRate);
        //查询注册总人数
        int amountOfUser = userService.queryAmountOfUser();
        request.setAttribute(Constants.AMOUNT_OF_USER, amountOfUser);
        //查询投资总金额
        Double bidMoney = bidInfoService.queryBidMoney();
        request.setAttribute(Constants.BID_MONEY, bidMoney);
        //产品展示
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("paramType", 0);
        paramMap.put("pageNumber", 0);
        paramMap.put("pageSize", 1);
        List<LoanInfo> loanInfoX = loanInfoService.queryProduct(paramMap);
        paramMap.put("paramType",1);
        paramMap.put("pageSize", 4);
        List<LoanInfo> loanInfoY = loanInfoService.queryProduct(paramMap);
        paramMap.put("paramType",2);
        paramMap.put("pageSize", 8);
        List<LoanInfo> loanInfoZ = loanInfoService.queryProduct(paramMap);
        model.addAttribute("loanInfoListX",loanInfoX);
        model.addAttribute("loanInfoListY",loanInfoY);
        model.addAttribute("loanInfoListZ",loanInfoZ);

        //从token中获取用户的信息,并且存放在session中。
        String token = ServletUtils.getToken(request);
        if (token != null){
            String phone = jwtTokenUtil.getUserIdFromToken(token);
            String username = jwtTokenUtil.getUserNameFromToken(token);
            if(phone != null){
                User user = new User();
                user.setPhone(phone);
                user.setName(username);
                request.getSession().setAttribute(Constants.LOGIN_USER_INFO, user);
            }
        }else {
            request.getSession().removeAttribute(Constants.LOGIN_USER_INFO);
        }

        return "index";
    }

    @RequestMapping("/user/getBalance")
    public @ResponseBody String getBalance(HttpServletRequest request){
        User user  = (User) request.getSession().getAttribute(Constants.LOGIN_USER_INFO);
        Integer uid = user.getId();
        Double balance = accountService.getBalance(uid);
        return String.valueOf(balance);
    }

//    @RequestMapping("/loan/logout")
//    public String logout(HttpServletRequest request){
//
//        request.getSession().removeAttribute("LoginUserInfo");
//
//        return "redirect:/auth/logout";
//    }
}
