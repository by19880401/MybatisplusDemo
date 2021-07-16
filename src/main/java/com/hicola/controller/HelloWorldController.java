package com.hicola.controller;

import com.hicola.model.User;
import com.hicola.service.IHelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author baiyang
 * @date 2021/7/16
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private IHelloWorldService helloWorldService;

    @PostMapping("/allUsers")
    public List<User> findAllUsers() {
        List<User> userList = helloWorldService.findAllUsers();
        logger.info("Find users: {}", userList.size());
        return userList;
    }

}
