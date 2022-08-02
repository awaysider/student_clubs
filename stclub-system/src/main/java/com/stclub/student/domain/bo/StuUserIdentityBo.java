package com.stclub.student.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;
import java.util.Date;


/**
 * 用户身份管理业务对象 stu_user_identity
 *
 * @author luo
 * @date 2022-03-08
 */

@Data
@ApiModel("用户身份管理业务对象")
public class StuUserIdentityBo {

    /**
     * 用户身份主键id
     */
    @ApiModelProperty(value = "用户身份主键id")
    private Long identityId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 社团id
     */
    @ApiModelProperty(value = "社团id")
    private Long corporationId;

    /**
     * 社团名
     */
    @ApiModelProperty(value = "社团名")
    private String corporationName;

    /**
     * 身份状态（0表示社长，1表示成员）
     */
    @ApiModelProperty(value = "身份状态（0表示社长，1表示成员）")
    private Long userStatus;

    /**
     * 用户状态（0表示正常，1表示停用）
     */
    @ApiModelProperty(value = "用户状态（0表示正常，1表示停用）")
    private Long status;

    /**
     * 入社日期
     */
    @ApiModelProperty(value = "入社日期")
    private Date joinTime;


}