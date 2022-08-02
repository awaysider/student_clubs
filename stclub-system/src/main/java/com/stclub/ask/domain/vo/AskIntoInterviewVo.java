package com.stclub.ask.domain.vo;

	import java.util.Date;

	import com.baomidou.mybatisplus.annotation.TableId;
	import com.fasterxml.jackson.annotation.JsonFormat;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 入社面试邀请管理视图对象 ask_into_interview
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@ApiModel("入社面试邀请管理视图对象")
public class AskIntoInterviewVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 面试邀请主键id
	 */
	@Excel(name = "面试邀请主键id")
	@ApiModelProperty("面试邀请主键id")
	private Long intoInterviewId;
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
			 * 面试时间
			 */
			@Excel(name = "面试时间")
		@ApiModelProperty("面试时间")
		private Date interviewTime;

			/**
			 * 面试状态（0待面试，1面试通过，面试未通过）
			 */
			@Excel(name = "面试状态", cellType = ColumnType.NUMERIC)
		@ApiModelProperty("面试状态（0待面试，1面试通过，面试未通过）")
		private Long status;


}
