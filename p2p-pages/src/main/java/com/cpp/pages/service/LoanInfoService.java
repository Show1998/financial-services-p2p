package com.cpp.pages.service;

import com.cpp.common.pojo.PageAndVo;
import com.cpp.pages.pojo.LoanInfo;


import java.util.List;
import java.util.Map;

public interface LoanInfoService {
    double queryAvgRate();

    List<LoanInfo> queryProduct(Map<String,Object> map);

    Integer queryAmountOfProdunt(Map<String, Object> map);

    PageAndVo queryProductByPage(Map<String, Object> map);

    LoanInfo queryProductById(Integer id);
}
