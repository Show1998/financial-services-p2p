package com.cpp.pages.service;

import com.cpp.pages.pojo.BidInfo;
import java.util.List;

public interface BidInfoService {
    Double queryBidMoney();

    List<BidInfo> queryConsumerByProductId(Integer id);
}
