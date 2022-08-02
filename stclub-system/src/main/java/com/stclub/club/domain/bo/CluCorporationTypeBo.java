package com.stclub.club.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 社团类型业务对象 clu_corporation_type
 *
 * @author ruoyi
 * @date 2022-03-09
 */

@Data
@ApiModel("社团类型业务对象")
public class CluCorporationTypeBo {

    /**
     * 社团类型id
     */
    @ApiModelProperty(value = "社团类型id")
    private Long coporationTypeId;

    /**
     * 社团类型名
     */
    @ApiModelProperty(value = "社团类型名")
    private String typeName;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String introduce;


}
