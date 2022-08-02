package com.stclub.student.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.stclub.common.annotation.Excel;
import com.stclub.common.annotation.Excel.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 用户信息视图对象 stu_user
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@ApiModel("用户信息视图对象")
public class StuUserVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@Excel(name = "用户id")
	@ApiModelProperty("用户id")
	private Long userId;

			/**
			 * 学院id
			 */
			@Excel(name = "学院id")
		@ApiModelProperty("学院id")
		private Long college;

	/**
	 * 学院名
	 */
	@Excel(name = "学院名")
	@ApiModelProperty("学院名")
	private String collegeName;

			/**
			 * 用户名
			 */
			@Excel(name = "用户名")
		@ApiModelProperty("用户名")
		private String userName;

			/**
			 * 用户昵称
			 */
			@Excel(name = "用户昵称")
		@ApiModelProperty("用户昵称")
		private String nickName;

			/**
			 * 性别
			 */
			@Excel(name = "性别")
		@ApiModelProperty("性别")
		private String sex;

			/**
			 * logo
			 */
			@Excel(name = "logo")
		@ApiModelProperty("logo")
		private String logo;

			/**
			 * 用户账号
			 */
			@Excel(name = "用户账号")
		@ApiModelProperty("用户账号")
		private String userAccount;

			/**
			 * 用户密码
			 */
			@Excel(name = "用户密码")
		@ApiModelProperty("用户密码")
		private String password;

			/**
			 * 手机号
			 */
			@Excel(name = "手机号")
		@ApiModelProperty("手机号")
		private String phone;

			/**
			 * 邮箱
			 */
			@Excel(name = "邮箱")
		@ApiModelProperty("邮箱")
		private String email;

			/**
			 * 账号状态(0正常，1停用)
			 */
			@Excel(name = "账号状态(0正常，1停用)")
		@ApiModelProperty("账号状态(0正常，1停用)")
		private Long status;

	/**
	 * 创建时间
	 */
	@Excel(name = "创建时间")
	@ApiModelProperty("创建时间")
	private Date createTime;



}
