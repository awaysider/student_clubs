package com.stclub.ask.mapper;

import com.stclub.ask.domain.AskIntoCorporation;
import com.stclub.ask.domain.bo.AskIntoCorporationBo;
import com.stclub.ask.domain.vo.AskIntoCorporationVo;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户入社申请管理Mapper接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface AskIntoCorporationMapper extends BaseMapperPlus<AskIntoCorporation> {

    List<AskIntoCorporationVo> getAskIntoCorporationList(AskIntoCorporationBo bo);

    List<AskIntoCorporationVo> getAskIntoCorporationListByUserId(@Param("userId")Long userId,@Param("status")int status);

}
