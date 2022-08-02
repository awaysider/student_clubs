package com.stclub.ask.domain.vo;

	import java.util.Date;

	import com.baomidou.mybatisplus.annotation.TableId;
	import com.fasterxml.jackson.annotation.JsonFormat;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
	import org.springframework.format.annotation.DateTimeFormat;


/**
 * 活动申请管理视图对象 ask_set_activity
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@ApiModel("活动申请管理视图对象")
public class AskSetActivityVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 申请id
	 */
	@Excel(name = "申请id")
	@ApiModelProperty("申请id")
	private Long setActivityId;
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
	 * 社团名
	 */
	@Excel(name = "社团名")
	@ApiModelProperty("社团名")
	private String corporationName;

			/**
			 * 活动名
			 */
			@Excel(name = "活动名")
		@ApiModelProperty("活动名")
		private String activityName;

			/**
			 * 活动预算
			 */
			@Excel(name = "活动预算")
		@ApiModelProperty("活动预算")
		private String activityBudget;

			/**
			 * 活动最大人数
			 */
			@Excel(name = "活动最大人数")
		@ApiModelProperty("活动最大人数")
		private Long maxNumber;

			/**
			 * 活动开始时间
			 */
			@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			@Excel(name = "活动开始时间")
		@ApiModelProperty("活动开始时间")
		private Date startTime;

			/**
			 * 活动结束时间
			 */
			@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			@Excel(name = "活动结束时间")
		@ApiModelProperty("活动结束时间")
		private Date endTime;

			/**
			 * 活动举办地点
			 */
			@Excel(name = "活动举办地点")
		@ApiModelProperty("活动举办地点")
		private String activityPlace;

			/**
			 * 活动举办内容
			 */
			@Excel(name = "活动举办内容")
		@ApiModelProperty("活动举办内容")
		private String activityContent;

			/**
			 * 创建时间
			 */
			@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			@Excel(name = "创建时间")
		@ApiModelProperty("创建时间")
		private Date createTime;

			/**
			 * 申请状态（0待审，1申请通过，2申请未通过）
			 */
			@Excel(name = "申请状态", cellType = ColumnType.NUMERIC)
		@ApiModelProperty("申请状态（0待审，1申请通过，2申请未通过）")
		private Long status;


}
