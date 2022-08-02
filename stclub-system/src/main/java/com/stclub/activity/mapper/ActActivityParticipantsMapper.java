package com.stclub.activity.mapper;

import com.stclub.activity.domain.ActActivityParticipants;
import com.stclub.activity.domain.bo.ActActivityParticipantsBo;
import com.stclub.activity.domain.vo.ActActivityParticipantsVo;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;

import java.util.List;

/**
 * 活动参加人员管理Mapper接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface ActActivityParticipantsMapper extends BaseMapperPlus<ActActivityParticipants> {

    List<ActActivityParticipantsVo> getActivityParticipantsList(ActActivityParticipantsBo bo);
}
