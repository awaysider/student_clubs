package com.stclub.student.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;
import java.util.Date;


/**
 * 用户信息业务对象 stu_user
 *
 * @author luo
 * @date 2022-03-08
 */

@Data
@ApiModel("用户信息业务对象")
public class StuUserBo {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 学院id
     */
    @ApiModelProperty(value = "学院id")
    private Long college;

    /**
     * 学院名
     */
    @ApiModelProperty(value = "学院名")
    private String collegeName;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * logo
     */
    @ApiModelProperty(value = "logo")
    private String logo;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String userAccount;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 账号状态(0正常，1停用)
     */
    @ApiModelProperty(value = "账号状态(0正常，1停用)")
    private Long status;




}
