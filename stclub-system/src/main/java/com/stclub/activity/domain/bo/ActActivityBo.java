package com.stclub.activity.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 活动信息管理业务对象 act_activity
 *
 * @author luo
 * @date 2022-03-08
 */

@Data
@ApiModel("活动信息管理业务对象")
public class ActActivityBo {

    /**
     * 活动主键id
     */
    @ApiModelProperty(value = "活动主键id")
    private Long actId;

    /**
     * 活动编号
     */
    @ApiModelProperty(value = "活动编号")
    private String activityCode;

    /**
     * 举办人id
     */
    @ApiModelProperty(value = "举办人id")
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
     * 活动名字
     */
    @ApiModelProperty(value = "活动名字")
    private String activityName;

    /**
     * 活动预算
     */
    @ApiModelProperty(value = "活动预算")
    private String activityBudget;

    /**
     * 参加最大人数
     */
    @ApiModelProperty(value = "参加最大人数")
    private Long maxNumber;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 活动地点
     */
    @ApiModelProperty(value = "活动地点")
    private String activityPlace;

    /**
     * 活动内容
     */
    @ApiModelProperty(value = "活动内容")
    private String activityContent;

    /**
     * 活动状态【1待进行，2进行中，3已结束】
     */
    @ApiModelProperty(value = "活动状态【1待进行，2进行中，3已结束】")
    private int status;


}
