package com.stclub.club.domain.vo;

import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 社团类型视图对象 clu_corporation_type
 *
 * @author ruoyi
 * @date 2022-03-09
 */
@Data
@ApiModel("社团类型视图对象")
public class CluCorporationTypeVo {

	private static final long serialVersionUID = 1L;

			/**
			 * 社团类型名
			 */
			@Excel(name = "社团类型名")
		@ApiModelProperty("社团类型名")
		private String typeName;
	/**
	 * 介绍
	 */
	@Excel(name = "介绍")
	@ApiModelProperty("介绍")
	private String introduce;


}
