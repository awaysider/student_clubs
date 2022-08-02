package com.stclub.ask.mapper;

import com.stclub.ask.domain.AskSetCorporation;
import com.stclub.ask.domain.bo.AskSetCorporationBo;
import com.stclub.ask.domain.vo.AskSetCorporationVo;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;

import java.util.List;

/**
 * 成员创建社团申请管理Mapper接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface AskSetCorporationMapper extends BaseMapperPlus<AskSetCorporation> {

    List<AskSetCorporationVo> getAskSetCorporationList(AskSetCorporationBo bo);

}
