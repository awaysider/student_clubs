package com.stclub.ask.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 活动申请管理对象 ask_set_activity
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@Accessors(chain = true)
@TableName("ask_set_activity")
public class AskSetActivity implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 申请id
     */
        @TableId(value = "set_activity_id")
    private Long setActivityId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 社团id
     */
    private Long corporationId;
    /**
     * 活动名
     */
    private String activityName;
    /**
     * 活动预算
     */
    private String activityBudget;
    /**
     * 活动最大人数
     */
    private Long maxNumber;
    /**
     * 活动开始时间
     */
    private Date startTime;
    /**
     * 活动结束时间
     */
    private Date endTime;
    /**
     * 活动举办地点
     */
    private String activityPlace;
    /**
     * 活动举办内容
     */
    private String activityContent;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 申请状态（0待审，1申请通过，2申请未通过）
     */
    private Long status;

}
