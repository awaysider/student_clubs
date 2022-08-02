package com.stclub.activity.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 活动参加人员管理业务对象 act_activity_participants
 *
 * @author luo
 * @date 2022-03-08
 */

@Data
@ApiModel("活动参加人员管理业务对象")
public class ActActivityParticipantsBo {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long participantsId;

    /**
     * 活动编号
     */
    @ApiModelProperty(value = "活动编号")
    private String activityCode;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Long actId;

    /**
     * 活动名字
     */
    @ApiModelProperty(value = "活动名字")
    private String activityName;

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


}
