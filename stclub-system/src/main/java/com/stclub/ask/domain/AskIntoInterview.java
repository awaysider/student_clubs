package com.stclub.ask.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 入社面试邀请管理对象 ask_into_interview
 *
 * @author luo
 * @date 2022-03-09
 */
@Data
@Accessors(chain = true)
@TableName("ask_into_interview")
public class AskIntoInterview implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 面试邀请主键id
     */
        @TableId(value = "into_interview_id")
    private Long intoInterviewId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 面试时间
     */
    private Date interviewTime;
    /**
     * 面试状态（0待面试，1面试通过，面试未通过）
     */
    private Long status;

}
