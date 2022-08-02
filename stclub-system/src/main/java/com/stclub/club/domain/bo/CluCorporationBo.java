package com.stclub.club.domain.bo;

import com.stclub.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;
import java.util.Date;


/**
 * 社团业务对象 clu_corporation
 *
 * @author luo
 * @date 2022-03-09
 */

@Data
@ApiModel("社团业务对象")
public class CluCorporationBo {

    /**
     * 社团主键id
     */
    @ApiModelProperty(value = "社团主键id")
    private Long corporationId;

    /**
     * 社团编号
     */
    @ApiModelProperty(value = "社团编号")
    private String corporationCode;

    /**
     * 社团类型id
     */
    @ApiModelProperty(value = "社团类型id")
    private Long typeId;

    /**
     * 社团名
     */
    @ApiModelProperty(value = "社团名")
    private String corporationName;

    /**
     * 社团logo
     */
    @ApiModelProperty(value = "社团logo")
    private String logo;

    /**
     * 成员数量
     */
    @ApiModelProperty(value = "成员数量")
    private Long number;

    /**
     * 社团标语
     */
    @ApiModelProperty(value = "社团标语")
    private String slogan;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;


}
