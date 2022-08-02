package com.stclub.activity.domain.vo;

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
 * 活动信息管理视图对象 act_activity
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@ApiModel("活动信息管理视图对象")
public class ActActivityVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 活动主键id
	 */
	@Excel(name = "活动主键id")
	@ApiModelProperty("活动主键id")
	private Long actId;

	/**
	 * 活动编号
	 */
	@Excel(name = "活动编号")
	@ApiModelProperty("活动编号")
	private String activityCode;
			/**
			 * 举办人id
			 */
			@Excel(name = "举办人id")
		@ApiModelProperty("举办人id")
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
			 * 活动名字
			 */
			@Excel(name = "活动名字")
		@ApiModelProperty("活动名字")
			private String activityName;

			/**
			 * 活动预算
			 */
			@Excel(name = "活动预算")
		@ApiModelProperty("活动预算")
		private String activityBudget;

			/**
			 * 参加最大人数
			 */
			@Excel(name = "参加最大人数")
		@ApiModelProperty("参加最大人数")
		private Long maxNumber;

			/**
			 * 开始时间
			 */
			@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			@Excel(name = "开始时间")
		@ApiModelProperty("开始时间")
		private Date startTime;

			/**
			 * 结束时间
			 */
			@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			@Excel(name = "结束时间")
		@ApiModelProperty("结束时间")
		private Date endTime;

			/**
			 * 活动地点
			 */
			@Excel(name = "活动地点")
		@ApiModelProperty("活动地点")
		private String activityPlace;

			/**
			 * 活动内容
			 */
			@Excel(name = "活动内容")
		@ApiModelProperty("活动内容")
		private String activityContent;

	/**
	 * 活动状态【1待进行，2进行中，3已结束】
	 */
	@Excel(name = "活动状态【1待进行，2进行中，3已结束】")
	@ApiModelProperty("活动状态【1待进行，2进行中，3已结束】")
	private int status;


}
