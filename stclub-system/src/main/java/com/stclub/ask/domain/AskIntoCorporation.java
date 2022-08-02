package com.stclub.ask.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
 * 用户入社申请管理对象 ask_into_corporation
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@Accessors(chain = true)
@TableName("ask_into_corporation")
public class AskIntoCorporation implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 入团申请主键id
     */
        @TableId(value = "into_corporation_id")
    private Long intoCorporationId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 社团id
     */
    private Long corporationId;
    /**
     * 申请状态（0待审，1申请通过，2申请未通过）
     */
    private int status;
    /**
     * 申请理由
     */
    private String reason;

}
