package com.stclub.student.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户身份管理对象 stu_user_identity
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@Accessors(chain = true)
@TableName("stu_user_identity")
public class StuUserIdentity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户身份主键id
     */
    @TableId(value = "identity_id")
    private Long identityId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 社团id
     */
    private Long corporationId;
    /**
     * 身份状态（0表示社长，1表示成员）
     */
    private Long userStatus;
    /**
     * 用户状态（0表示正常，1表示停用）
     */
    private Long status;
    /**
     * 入社日期
     */
    private Date joinTime;

}