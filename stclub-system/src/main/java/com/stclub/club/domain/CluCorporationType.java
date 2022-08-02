package com.stclub.club.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
 * 社团类型对象 clu_corporation_type
 *
 * @author ruoyi
 * @date 2022-03-09
 */
@Data
@Accessors(chain = true)
@TableName("clu_corporation_type")
public class CluCorporationType implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 社团类型id
     */
        @TableId(value = "coporation_type_id")
    private Long coporationTypeId;
    /**
     * 社团类型名
     */
    private String typeName;
    /**
     * 介绍
     */
    private String introduce;

}
