package com.stclub.ask.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 入社面试邀请管理业务对象 ask_into_interview
 *
 * @author luo
 * @date 2022-03-09
 */

@Data
@ApiModel("入社面试邀请管理业务对象")
public class AskIntoInterviewBo {

    /**
     * 面试邀请主键id
     */
    @ApiModelProperty(value = "面试邀请主键id")
    private Long intoInterviewId;

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
     * 面试时间
     */
    @ApiModelProperty(value = "面试时间")
    private Date interviewTime;

    /**
     * 面试状态（0待面试，1面试通过，面试未通过）
     */
    @ApiModelProperty(value = "面试状态（0待面试，1面试通过，面试未通过）")
    private Long status;


}
