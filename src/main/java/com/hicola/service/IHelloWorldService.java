package com.hicola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hicola.model.User;

import java.util.List;

/**
 * @author baiyang
 * @date 2021/7/16
 */
public interface IHelloWorldService extends IService<User> {

    /**
     * 查询所有人员信息，通过mapper.xml的方式
     * @return
     */
    List<User> findAllUsersByMapperXML();

    /**
     * 查询所有人员，通过mybatis-plus自带接口
     * @return
     */
    List<User> findAllUsersByInterface();

    /**
     * 添加一个用户
     */
    void addUser();

    /**
     * 生成用户数据（用于测试）
     */
    void generateUserData();
}
