package com.stclub.club.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;


/**
 * 社团对象 clu_corporation
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@Accessors(chain = true)
@TableName("clu_corporation")
public class CluCorporation implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 社团主键id
     */
        @TableId(value = "corporation_id")
    private Long corporationId;
    /**
     * 社团编号
     */
    private String corporationCode;
    /**
     * 社团类型id
     */
    private Long typeId;
    /**
     * 社团名
     */
    private String corporationName;
    /**
     * 社团logo
     */
    private String logo;
    /**
     * 成员数量
     */
    private Long number;
    /**
     * 社团标语
     */
    private String slogan;
    /**
     * 创建时间
     */
    private Date createTime;

}
