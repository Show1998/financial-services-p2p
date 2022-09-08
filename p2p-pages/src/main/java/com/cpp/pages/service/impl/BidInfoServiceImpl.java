package com.cpp.pages.service.impl;


import com.cpp.common.Constants;
import com.cpp.pages.mapper.loan.BidInfoMapper;
import com.cpp.pages.pojo.BidInfo;
import com.cpp.pages.service.BidInfoService;
import org.apache.commons.lang3.ObjectUtils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BidInfoServiceImpl implements BidInfoService {
    @Resource
    BidInfoMapper bidInfoMapper;
    @Resource
    RedisTemplate redisTemplate;
    @Override
    public Double queryBidMoney() {
        Double bidMoney = (Double) redisTemplate.opsForValue().get(Constants.BID_MONEY);
        if(ObjectUtils.allNull(bidMoney)){
            synchronized (this){
                bidMoney = (Double) redisTemplate.opsForValue().get(Constants.BID_MONEY);
                if (ObjectUtils.allNull(bidMoney)){
                    bidMoney = bidInfoMapper.selectBidMoney();
                    redisTemplate.opsForValue().set(Constants.BID_MONEY, bidMoney,60, TimeUnit.SECONDS);
                }
            }
        }
        return bidMoney;
    }

    @Override
    public List<BidInfo> queryConsumerByProductId(Integer id) {
        return bidInfoMapper.selectConsumerById(id);
    }
}
