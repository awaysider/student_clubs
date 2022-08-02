package com.stclub.ask.mapper;

import com.stclub.ask.domain.AskIntoInterview;
import com.stclub.ask.domain.bo.AskIntoInterviewBo;
import com.stclub.ask.domain.vo.AskIntoInterviewVo;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;

import java.util.List;

/**
 * 入社面试邀请管理Mapper接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface AskIntoInterviewMapper extends BaseMapperPlus<AskIntoInterview> {

    List<AskIntoInterviewVo> getAskIntoInterviewList(AskIntoInterviewBo bo);
}
