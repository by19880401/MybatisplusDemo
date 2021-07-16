package com.hicola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hicola.mapper.UserMapper;
import com.hicola.model.User;
import com.hicola.service.IHelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 * @author baiyang
 * @date 2021/7/16
 */
@Service
public class HelloWorldServiceImpl extends ServiceImpl<UserMapper, User> implements IHelloWorldService {

    @Autowired
    private UserMapper userMapper;//这里会出现报警提示

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }
}