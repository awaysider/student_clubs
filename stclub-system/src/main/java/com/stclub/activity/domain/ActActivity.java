package com.stclub.activity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 活动信息管理对象 act_activity
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@Accessors(chain = true)
@TableName("act_activity")
public class ActActivity implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 活动主键id
     */
    @TableId(value = "act_id")
    private Long actId;
    /**
     * 活动编号
     */
    private String activityCode;
    /**
     * 举办人id
     */
    private Long userId;
    /**
     * 社团id
     */
    private Long corporationId;
    /**
     * 活动名字
     */
    private String activityName;
    /**
     * 活动预算
     */
    private String activityBudget;
    /**
     * 参加最大人数
     */
    private Long maxNumber;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 活动地点
     */
    private String activityPlace;
    /**
     * 活动内容
     */
    private String activityContent;
    /**
     * 活动状态【1待进行，2进行中，3已结束】
     */
    private int status;

}
