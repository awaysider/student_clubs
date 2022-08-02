package com.stclub.ask.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
 * 成员成为社长申请管理对象 ask_become_president
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@Accessors(chain = true)
@TableName("ask_become_president")
public class AskBecomePresident implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 成为社长申请主键id
     */
        @TableId(value = "president_id")
    private Long presidentId;
    /**
     * 申请用户id
     */
    private Long userId;
    /**
     * 原社长id
     */
    private Long originalUserId;
    /**
     * 社团id
     */
    private Long corporationId;
    /**
     * 申请状态（0待审，1申请通过，2申请未通过）
     */
    private Long status;
    /**
     * 申请理由
     */
    private String reason;

}
