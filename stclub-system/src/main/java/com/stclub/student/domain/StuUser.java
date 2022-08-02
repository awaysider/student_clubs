package com.stclub.student.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户信息对象 stu_user
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@Accessors(chain = true)
@TableName("stu_user")
public class StuUser implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
        @TableId(value = "user_id")
    private Long userId;
    /**
     * 学院id
     */
    private Long college;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 性别
     */
    private String sex;
    /**
     * logo
     */
    private String logo;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 账号状态(0正常，1停用)
     */
    private Long status;
    /**
     * 创建时间
     */
    private Date createTime;

}
