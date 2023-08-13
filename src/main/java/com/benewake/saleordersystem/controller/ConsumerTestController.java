package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.utils.Result;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Lcs
 * @since 2023年08月03 11:44
 * 描 述： TODO
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerTestController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/send")
    public Result sendMessage(String message){
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("data",message);
        map.put("current",current);
        rabbitTemplate.convertAndSend("NoticeDirectExchange", "notice", map, new CorrelationData(UUID.randomUUID().toString()));
        return Result.success();
    }
}
