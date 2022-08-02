package com.stclub.student.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 用户身份管理视图对象 stu_user_identity
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@ApiModel("用户身份管理视图对象")
public class StuUserIdentityVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户身份主键id
	 */
	@Excel(name = "用户身份主键id")
	@ApiModelProperty("用户身份主键id")
	private Long identityId;

	/**
	 * 用户id
	 */
	@Excel(name = "用户id")
	@ApiModelProperty("用户id")
	private Long userId;

	/**
	 * 用户名
	 */
	@Excel(name = "用户名")
	@ApiModelProperty("用户名")
	private String userName;

	/**
	 * 社团id
	 */
	@Excel(name = "社团id")
	@ApiModelProperty("社团id")
	private Long corporationId;

	/**
	 * 社团编号
	 */
	@Excel(name = "社团编号")
	@ApiModelProperty("社团编号")
	private String corporationCode;

	/**
	 * 社团名
	 */
	@Excel(name = "社团名")
	@ApiModelProperty("社团名")
	private String corporationName;

	/**
	 * 身份状态（0表示社长，1表示成员）
	 */
	@Excel(name = "身份状态", cellType = ColumnType.NUMERIC)
	@ApiModelProperty("身份状态（0表示社长，1表示成员）")
	private Long userStatus;

	/**
	 * 用户状态（0表示正常，1表示停用）
	 */
	@Excel(name = "用户状态", cellType = ColumnType.NUMERIC)
	@ApiModelProperty("用户状态（0表示正常，1表示停用）")
	private Long status;

	/**
	 * 入社日期
	 */
	@Excel(name = "入社日期")
	@ApiModelProperty("入社日期")
	private Date joinTime;

}