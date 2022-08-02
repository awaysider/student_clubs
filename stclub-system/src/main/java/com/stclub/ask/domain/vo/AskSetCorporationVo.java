package com.stclub.ask.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 成员创建社团申请管理视图对象 ask_set_corporation
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@ApiModel("成员创建社团申请管理视图对象")
public class AskSetCorporationVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 成员创建社团申请主键id
	 */
	@Excel(name = "成员创建社团申请主键id")
	@ApiModelProperty("成员创建社团申请主键id")
	private Long setCorporationId;
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
	 * logo
	 */
	@Excel(name = "logo")
	@ApiModelProperty("logo")
	private String logo;


			/**
			 * 社团名
			 */
			@Excel(name = "社团名")
		@ApiModelProperty("社团名")
		private String corporationName;

			/**
			 * 社团标语
			 */
			@Excel(name = "社团标语")
		@ApiModelProperty("社团标语")
		private String corporationSlogan;

			/**
			 * 社团类型id
			 */
			@Excel(name = "社团类型id")
		@ApiModelProperty("社团类型id")
		private Long corporationTypeId;

	/**
	 * 社团类型名
	 */
	@Excel(name = "社团类型名")
	@ApiModelProperty("社团类型名")
	private String typeName;

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
