package com.stclub.ask.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
 * 成员创建社团申请管理对象 ask_set_corporation
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@Accessors(chain = true)
@TableName("ask_set_corporation")
public class AskSetCorporation implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 成员创建社团申请主键id
     */
        @TableId(value = "set_corporation_id")
    private Long setCorporationId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 社团名
     */
    private String corporationName;
    /**
     * logo
     */
    private String logo;
    /**
     * 社团类型id
     */
    private String corporationSlogan;
    /**
     * 社团类型id
     */
    private Long corporationTypeId;
    /**
     * 申请状态（0待审，1申请通过，2申请未通过）
     */
    private Long status;
    /**
     * 申请理由
     */
    private String reason;

}
