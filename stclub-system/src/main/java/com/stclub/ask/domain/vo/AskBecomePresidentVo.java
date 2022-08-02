package com.stclub.ask.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 成员成为社长申请管理视图对象 ask_become_president
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@ApiModel("成员成为社长申请管理视图对象")
public class AskBecomePresidentVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 成为社长申请主键id
	 */
	@Excel(name = "成为社长申请主键id")
	@ApiModelProperty("成为社长申请主键id")
	private Long presidentId;
			/**
			 * 申请用户id
			 */
			@Excel(name = "申请用户id")
		@ApiModelProperty("申请用户id")
		private Long userId;
	/**
	 * 用户名
	 */
	@Excel(name = "用户名")
	@ApiModelProperty("用户名")
	private String userName;

			/**
			 * 原社长id
			 */
			@Excel(name = "原社长id")
		@ApiModelProperty("原社长id")
		private Long originalUserId;

	/**
	 * 原社长名
	 */
	@Excel(name = "原社长名")
	@ApiModelProperty("原社长名")
	private String originalUserName;

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
			 * 申请状态（0待审，1申请通过，2申请未通过）
			 */
			@Excel(name = "申请状态", cellType = ColumnType.NUMERIC)
		@ApiModelProperty("申请状态（0待审，1申请通过，2申请未通过）")
		private Long status;

			/**
			 * 申请理由
			 */
			@Excel(name = "申请理由")
		@ApiModelProperty("申请理由")
		private String reason;


}
