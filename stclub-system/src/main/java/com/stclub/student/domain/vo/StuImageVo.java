package com.stclub.student.domain.vo;

import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 首页轮番图视图对象 stu_image
 *
 * @author ruoyi
 * @date 2022-04-12
 */
@Data
@ApiModel("首页轮番图视图对象")
public class StuImageVo {

	private static final long serialVersionUID = 1L;

			/**
			 * 图片地址
			 */
			@Excel(name = "图片地址")
		@ApiModelProperty("图片地址")
		private String image;


}
