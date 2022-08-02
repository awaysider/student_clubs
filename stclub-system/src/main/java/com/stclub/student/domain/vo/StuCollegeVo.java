package com.stclub.student.domain.vo;

import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 学院管理视图对象 stu_college
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@ApiModel("学院管理视图对象")
public class StuCollegeVo {

	private static final long serialVersionUID = 1L;

			/**
			 * 学院名
			 */
			@Excel(name = "学院名")
		@ApiModelProperty("学院名")
		private String collegeName;


}
