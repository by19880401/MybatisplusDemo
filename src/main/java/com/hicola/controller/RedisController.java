package com.hicola.controller;

import com.hicola.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * redis demo
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/8/18
 * @
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public void getValue(@RequestParam("key") String key) {
        logger.info("Receive key [{}] in the request", key);
        redisService.getValue(key);
    }


}
