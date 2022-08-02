package com.stclub.ask.domain.bo;

import com.stclub.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 用户入社申请管理业务对象 ask_into_corporation
 *
 * @author luo
 * @date 2022-03-09
 */

@Data
@ApiModel("用户入社申请管理业务对象")
public class AskIntoCorporationBo {

    /**
     * 入团申请主键id
     */
    @ApiModelProperty(value = "入团申请主键id")
    private Long intoCorporationId;

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
     * 社团id
     */
    @ApiModelProperty(value = "社团id")
    private Long corporationId;

    /**
     * 社团名
     */
    @ApiModelProperty("社团名")
    private String corporationName;

    /**
     * 申请状态（0待审，1申请通过，2申请未通过）
     */
    @ApiModelProperty(value = "申请状态（0待审，1申请通过，2申请未通过）")
    private int status;

    /**
     * 申请理由
     */
    @ApiModelProperty(value = "申请理由")
    private String reason;


}
