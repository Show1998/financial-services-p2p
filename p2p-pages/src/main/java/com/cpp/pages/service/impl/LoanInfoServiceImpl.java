package com.cpp.pages.service.impl;

import com.cpp.common.Constants;
import com.cpp.common.pojo.PageAndVo;
import com.cpp.pages.mapper.loan.LoanInfoMapper;
import com.cpp.pages.pojo.LoanInfo;
import com.cpp.pages.service.LoanInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoanInfoServiceImpl implements LoanInfoService {

    @Resource
    LoanInfoMapper loanInfoMapper;

    @Resource
    RedisTemplate<Object,Object> redisTemplate;

    @Override
    public double queryAvgRate() {
        //先从redis中获取数据
        Double avgRate = (Double) redisTemplate.opsForValue().get(Constants.AVG_RATE);
        if(ObjectUtils.allNull(avgRate)){
            synchronized (this){
                avgRate = (Double) redisTemplate.opsForValue().get(Constants.AVG_RATE);
                if(ObjectUtils.allNull(avgRate)){
                    avgRate = loanInfoMapper.selectAvgRate();
                    redisTemplate.opsForValue().set(Constants.AVG_RATE, avgRate, 60, TimeUnit.SECONDS);
                }
            }
        }
        return avgRate;
    }

    @Override
    public List<LoanInfo> queryProduct(Map<String, Object> map) {
        return loanInfoMapper.selectProduct(map);
    }

    @Override
    public Integer queryAmountOfProdunt(Map<String, Object> map){
        return loanInfoMapper.selectAmountOfProduct(map);
    }

    @Override
    public PageAndVo queryProductByPage(Map<String, Object> map) {
        List<LoanInfo> loanInfos = this.queryProduct(map);
        Integer integer = this.queryAmountOfProdunt(map);
        PageAndVo<LoanInfo> pageAndVo = new PageAndVo<>(loanInfos,integer);
        return pageAndVo;
    }

    @Override
    public LoanInfo queryProductById(Integer id) {
        return loanInfoMapper.selectByPrimaryKey(id);
    }

}
