package com.hicola.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    private final UserMapper userMapper;

    @Autowired
    public HelloWorldServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAllUsersByMapperXML() {
        return userMapper.findAllUsers();
    }

    @Override
    public List<User> findAllUsersByInterface() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.isNotNull("book_id");// 这里随便乱定的查询条件
        return userMapper.selectList(qw);
    }

    @Override
    public void addUser() {
        User user = new User();
        user.setName("QiQi");
        user.setAge(3);
        user.setSex("M");
        user.setAddress("Xi'an Road.3 GaoXinHuaFu XiaoQu");
        user.setBookId("003");
        userMapper.insert(user);
    }
}
