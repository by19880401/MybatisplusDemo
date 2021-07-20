package com.hicola.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author baiyang
 * @date 2021/7/16
 */
@Data
@TableName("p_user")
public class User {
    @TableId
    private String fid;
    private String name;
    private Integer age;
    private String sex;
    private String address;
    private String bookId;
}
