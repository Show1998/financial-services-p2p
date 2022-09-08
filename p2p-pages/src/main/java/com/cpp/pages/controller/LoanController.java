package com.cpp.pages.controller;

import com.cpp.common.pojo.PageAndVo;
import com.cpp.pages.service.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoanController {

    @Autowired
    LoanInfoService loanInfoService;

    @RequestMapping("/loan/loan")
    public String toLoanPage(
                            @RequestParam(value = "ptype",required = false) Integer pType,
                            @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                            Model model,
                            HttpServletRequest request
                            ){
        Integer pageSize = 9;
        Integer pageNumber = (currentPage-1)*pageSize;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("paramType", pType);
        paramMap.put("pageNumber", pageNumber);
        paramMap.put("pageSize", pageSize);
        PageAndVo pageAndVo = loanInfoService.queryProductByPage(paramMap);
        model.addAttribute("loanInfoList", pageAndVo.getList());
        model.addAttribute("total",pageAndVo.getTotal());

        Integer totalPage = pageAndVo.getTotal()% 9 == 0 ? pageAndVo.getTotal()/9 :pageAndVo.getTotal()/9 + 1;
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("ptype",pType);
        return "loan";

    }
}
