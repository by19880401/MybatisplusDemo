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

//    @Autowired /**属性注入（Spring4.0之后已不推荐）*/
    private final IHelloWorldService helloWorldService;

    @Autowired
    /**构造器注入，如果HelloWorldController强依赖IHelloWorldService，即前者不允许后者为NULL，则推荐，此时属性应用final修饰*/
    public HelloWorldController(IHelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }


//    @Autowired /**setter方法注入，IHelloWorldService对于HelloWorldController来说是可选的，可有可无的，则推荐*/
//    public void setHelloWorldService(IHelloWorldService helloWorldService) {
//        this.helloWorldService = helloWorldService;
//    }

    @PostMapping("/allUsers")
    public List<User> findAllUsers() {
        List<User> userList = helloWorldService.findAllUsersByInterface();
        logger.info("Find users: {}", userList.size());
        return userList;
    }

    @PostMapping("/addUser")
    public void addUser() {
        helloWorldService.addUser();
        logger.info("Add user successfully");
    }




}
