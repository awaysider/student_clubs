package com.stclub.club.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 社团视图对象 clu_corporation
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@ApiModel("社团视图对象")
public class CluCorporationVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 社团主键id
	 */
	@Excel(name = "社团主键id")
	@ApiModelProperty("社团主键id")
	private Long corporationId;

	/**
	 * 社团编号
	 */
	@Excel(name = "社团编号")
	@ApiModelProperty("社团编号")
	private String corporationCode;

			/**
			 * 社团类型id
			 */
		@Excel(name = "社团类型id")
		@ApiModelProperty("社团类型id")
		private Long typeId;

	/**
	 * 社团类型名
	 */
	@Excel(name = "社团类型名")
	@ApiModelProperty("社团类型名")
	private String typeName;

	/**
	 * 社团名
	 */
	@Excel(name = "社团名")
	@ApiModelProperty("社团名")
	private String corporationName;
	/**
	 * 社团logo
	 */
	@Excel(name = "社团logo")
	@ApiModelProperty("社团logo")
	private String logo;

			/**
			 * 成员数量
			 */
			@Excel(name = "成员数量")
		@ApiModelProperty("成员数量")
		private Long number;

			/**
			 * 社团标语
			 */
			@Excel(name = "社团标语")
		@ApiModelProperty("社团标语")
		private String slogan;

	/**
	 * 创建时间
	 */
	@Excel(name = "创建时间")
	@ApiModelProperty("创建时间")
	private Date createTime;

	/**
	 * 入社日期
	 */

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "入社日期")
	@ApiModelProperty("入社日期")
	private Date joinTime;

	/**
	 * 身份状态（0表示社长，1表示成员）
	 */
	@Excel(name = "身份状态", cellType = ColumnType.NUMERIC)
	@ApiModelProperty("身份状态（0表示社长，1表示成员）")
	private Long userStatus;


}
