package com.stclub.ask.mapper;

import com.stclub.ask.domain.AskBecomePresident;
import com.stclub.ask.domain.bo.AskBecomePresidentBo;
import com.stclub.ask.domain.vo.AskBecomePresidentVo;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成员成为社长申请管理Mapper接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface AskBecomePresidentMapper extends BaseMapperPlus<AskBecomePresident> {

    List<AskBecomePresidentVo> getAskBecomePresidentList(AskBecomePresidentBo bo);

    List<AskBecomePresidentVo> getAskBecomePresidentListByUserId(@Param("userId")Long userId, @Param("status")long status);

}
