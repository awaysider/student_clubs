package com.stclub.ask.domain.bo;

import com.stclub.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 成员创建社团申请管理业务对象 ask_set_corporation
 *
 * @author luo
 * @date 2022-03-09
 */

@Data
@ApiModel("成员创建社团申请管理业务对象")
public class AskSetCorporationBo {

    /**
     * 成员创建社团申请主键id
     */
    @ApiModelProperty(value = "成员创建社团申请主键id")
    private Long setCorporationId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 社团名
     */
    @ApiModelProperty(value = "社团名")
    private String corporationName;

    /**
     * logo
     */
    @ApiModelProperty(value = "logo")
    private String logo;

    /**
     * 社团类型id
     */
    @ApiModelProperty(value = "社团类型id")
    private String corporationSlogan;

    /**
     * 社团类型id
     */
    @ApiModelProperty(value = "社团类型id")
    private Long corporationTypeId;

    /**
     * 申请状态（0待审，1申请通过，2申请未通过）
     */
    @ApiModelProperty(value = "申请状态（0待审，1申请通过，2申请未通过）")
    private Long status;

    /**
     * 申请理由
     */
    @ApiModelProperty(value = "申请理由")
    private String reason;


}
