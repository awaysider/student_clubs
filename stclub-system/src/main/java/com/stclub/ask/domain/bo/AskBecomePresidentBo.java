package com.stclub.ask.domain.bo;

import com.stclub.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 成员成为社长申请管理业务对象 ask_become_president
 *
 * @author luo
 * @date 2022-03-09
 */

@Data
@ApiModel("成员成为社长申请管理业务对象")
public class AskBecomePresidentBo {

    /**
     * 成为社长申请主键id
     */
    @ApiModelProperty(value = "成为社长申请主键id")
    private Long presidentId;

    /**
     * 申请用户id
     */
    @ApiModelProperty(value = "申请用户id")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 原社长id
     */
    @ApiModelProperty(value = "原社长id")
    private Long originalUserId;

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
    private Long status;

    /**
     * 申请理由
     */
    @ApiModelProperty(value = "申请理由")
    private String reason;


}
