package com.hicola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hicola.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper 接口，直接与UserMapper.xml里的SQL关联了
 * @author baiyang
 * @date 2021/7/16
 */
@Repository // 添加了此注解后，UserMapper注入到*impl类中才不会出现报警提示，目前不知原因为何？
public interface UserMapper extends BaseMapper<User> {
    List<User> findAllUsers();
}
