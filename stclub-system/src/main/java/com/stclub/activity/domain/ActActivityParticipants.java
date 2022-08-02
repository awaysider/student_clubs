package com.stclub.activity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
 * 活动参加人员管理对象 act_activity_participants
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@Accessors(chain = true)
@TableName("act_activity_participants")
public class ActActivityParticipants implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "participants_id")
    private Long participantsId;
    /**
     * 活动id
     */
    private Long actId;
    /**
     * 用户id
     */
    private Long userId;

}
