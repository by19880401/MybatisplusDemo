package com.hicola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hicola.model.User;

import java.util.List;

/**
 * @author baiyang
 * @date 2021/7/16
 */
public interface IHelloWorldService extends IService<User> {

    List<User> findAllUsers();
}
