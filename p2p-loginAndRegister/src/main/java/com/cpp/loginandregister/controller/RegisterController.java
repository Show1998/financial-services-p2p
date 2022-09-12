package com.cpp.loginandregister.controller;


import com.alibaba.fastjson.JSONObject;
import com.cpp.loginandregister.pojo.User;
import com.cpp.loginandregister.service.RedisService;
import com.cpp.loginandregister.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/page/register")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/page/checkPhone")
    public @ResponseBody Map<String,Object> checkPhone(String phone){

        Map<String,Object> resultMap = new HashMap<>();
        User u = userService.queryPhoneIsRegister(phone);
        if (!Objects.nonNull(u)){
            resultMap.put("result", 1);
        }else {
            resultMap.put("result", 0);
        }
        return resultMap;
    }

    @PostMapping("/page/sendMessage")
    public @ResponseBody String sendMessage(String phone) throws DocumentException {
        //生成6位随机数
        String randNum = this.getCodeMessage(6);

        //TODO 调用第三方SDK发送短信
//        String url = "xxxx";
//        HttpClientUtils.doGet(url);

        String sendResult = "{\n" +
                "    \"code\": \"10000\",\n" +
                "    \"charge\": false,\n" +
                "    \"remain\": 0,\n" +
                "    \"msg\": \"查询成功\",\n" +
                "    \"result\": \"<?xml version=\\\"1.0\\\" encoding=\\\"utf-8\\\" ?><returnsms>\\n <returnstatus>Success</returnstatus>\\n <message>ok</message>\\n <remainpoint>-1111611</remainpoint>\\n <taskID>101609164</taskID>\\n <successCounts>1</successCounts></returnsms>\"\n" +
                "}";

        //解析发送结果
        JSONObject jsonObject = JSONObject.parseObject(sendResult);
        String code = jsonObject.getString("code");
        if(!StringUtils.equals(code, "10000")){
            return "-1";
        }
        String result = jsonObject.getString("result");
        Document document = DocumentHelper.parseText(result);
        Node node = document.selectSingleNode("/returnsms/returnstatus");
        String text = node.getText();
        if (!StringUtils.equals(text, "Success")){
            return "-2";
        }
        //将结果存入redis中
        redisService.put(phone, randNum);

        return "1";
    }

    public String getCodeMessage(int num){
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < num; i++){
            int randNum = random.nextInt(9);
            stringBuffer.append(randNum);
        }
        return stringBuffer.toString();
    }
}
