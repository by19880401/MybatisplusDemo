package com.hicola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hicola.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper 接口，直接与UserMapper.xml里的SQL关联了
 *
 * 当你的Mapper 继承BaseMapper接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 *
 * @author baiyang
 * @date 2021/7/16
 */
@Repository // 添加了此注解后，UserMapper注入到*impl类中才不会出现报警提示，目前不知原因为何？
public interface UserMapper extends BaseMapper<User> {
    List<User> findAllUsers();
}
