package com.stclub.ask.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户入社申请管理视图对象 ask_into_corporation
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@ApiModel("用户入社申请管理视图对象")
public class AskIntoCorporationVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 入团申请主键id
	 */
	@Excel(name = "入团申请主键id")
	@ApiModelProperty("入团申请主键id")
	private Long intoCorporationId;
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
			 * 申请状态（0待审，1申请通过，2申请未通过）
			 */
			@Excel(name = "申请状态", cellType = ColumnType.NUMERIC)
		@ApiModelProperty("申请状态（0待审，1申请通过，2申请未通过）")
		private int status;

			/**
			 * 申请理由
			 */
			@Excel(name = "申请理由")
		@ApiModelProperty("申请理由")
		private String reason;


}
