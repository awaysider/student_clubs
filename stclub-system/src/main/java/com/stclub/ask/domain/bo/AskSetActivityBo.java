package com.stclub.ask.domain.bo;

import com.stclub.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 活动申请管理业务对象 ask_set_activity
 *
 * @author luo
 * @date 2022-03-09
 */

@Data
@ApiModel("活动申请管理业务对象")
public class AskSetActivityBo {

    /**
     * 申请id
     */
    @ApiModelProperty(value = "申请id")
    private Long setActivityId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 社团id
     */
    @ApiModelProperty(value = "社团id")
    private Long corporationId;

    /**
     * 社团名
     */
    @ApiModelProperty("社团名")
    private String corporationName;

    /**
     * 活动名
     */
    @ApiModelProperty(value = "活动名")
    private String activityName;

    /**
     * 活动预算
     */
    @ApiModelProperty(value = "活动预算")
    private String activityBudget;

    /**
     * 活动最大人数
     */
    @ApiModelProperty(value = "活动最大人数")
    private Long maxNumber;

    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    /**
     * 活动举办地点
     */
    @ApiModelProperty(value = "活动举办地点")
    private String activityPlace;

    /**
     * 活动举办内容
     */
    @ApiModelProperty(value = "活动举办内容")
    private String activityContent;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 申请状态（0待审，1申请通过，2申请未通过）
     */
    @ApiModelProperty(value = "申请状态（0待审，1申请通过，2申请未通过）")
    private Long status;


}
