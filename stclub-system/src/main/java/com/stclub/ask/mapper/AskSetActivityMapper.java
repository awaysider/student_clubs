package com.stclub.ask.mapper;

import com.stclub.ask.domain.AskSetActivity;
import com.stclub.ask.domain.bo.AskSetActivityBo;
import com.stclub.ask.domain.vo.AskSetActivityVo;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;

import java.util.List;

/**
 * 活动申请管理Mapper接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface AskSetActivityMapper extends BaseMapperPlus<AskSetActivity> {

    List<AskSetActivityVo> getAskSetActivityList(AskSetActivityBo bo);
}
