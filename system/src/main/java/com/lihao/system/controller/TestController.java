package com.lihao.system.controller;

import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihao
 * @description:
 * @date 2022/3/3 15:03
 */
@RequestMapping("test")
@RestController
@Slf4j
@Api(tags = "test")
public class TestController {

    @GetMapping("do")
    public Map<String,String> test(){
        HashMap<String, String> map = Maps.newHashMap();
        map.put("code", "200");
        map.put("msg", "ok");
        return map;
    }
}
