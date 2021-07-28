package com.hicola.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hicola.mapper.UserMapper;
import com.hicola.model.User;
import com.hicola.service.IHelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 服务实现类
 *
 * @author baiyang
 * @date 2021/7/16
 */
@Service
public class HelloWorldServiceImpl extends ServiceImpl<UserMapper, User> implements IHelloWorldService {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

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
    public void addUser(String name, Integer age, String sex, String address, String bookId) {
        User user = new User();
        user.setName(name);
        user.setAge(null == age ? 0 : age);
        user.setSex(sex);
        user.setAddress(address);
        user.setBookId(bookId);
        userMapper.insert(user);
    }

    @Override
    public void generateUserData(Integer recordNum) {
        if (Objects.isNull(recordNum)) {
            logger.warn("recordNum is null, set 0 by default");
            recordNum = 0;
        }
        //首先删除数据
        userMapper.deleteAllUsers();
        // 组装数据
        List<User> uList = Lists.newArrayList();
        for (int i = 0; i < recordNum; i++) {
            User user = new User();
            user.setName("Panda No." + i);
            user.setAge(i);
            user.setSex(i % 2 == 0 ? "F" : "M");//Java取模,奇数为M，偶数为F
            user.setAddress("China Joke" + i);
            user.setBookId("000" + i);
            uList.add(user);
        }
        if (CollectionUtils.isEmpty(uList)) {
            logger.warn("Add {} users.", uList.size());
            return;
        }
        //批量保存数据
        this.saveBatch(uList);
        logger.info("Add {} users.", uList.size());
    }
}
