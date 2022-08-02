package com.stclub.activity.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 活动参加人员管理视图对象 act_activity_participants
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@ApiModel("活动参加人员管理视图对象")
public class ActActivityParticipantsVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Excel(name = "主键id")
	@ApiModelProperty("主键id")
	private Long participantsId;

			/**
			 * 活动id
			 */
			@Excel(name = "活动id")
		@ApiModelProperty("活动id")
		private Long actId;

	/**
	 * 活动编号
	 */
	@Excel(name = "活动编号")
	@ApiModelProperty("活动编号")
	private String activityCode;

	/**
	 * 活动名字
	 */
	@Excel(name = "活动名字")
	@ApiModelProperty("活动名字")
	private String activityName;

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


}
