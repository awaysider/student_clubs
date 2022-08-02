package com.stclub.activity.service;

import com.stclub.activity.domain.ActActivityParticipants;
import com.stclub.activity.domain.vo.ActActivityParticipantsVo;
import com.stclub.activity.domain.bo.ActActivityParticipantsBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 活动参加人员管理Service接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface IActActivityParticipantsService extends IServicePlus<ActActivityParticipants, ActActivityParticipantsVo> {
    /**
     * 根据新增业务对象插入活动参加人员管理
     * @param bo 活动参加人员管理新增业务对象
     * @return
     */
    Boolean insertByBo(ActActivityParticipantsBo bo);

    /**
     * 根据编辑业务对象修改活动参加人员管理
     * @param bo 活动参加人员管理编辑业务对象
     * @return
     */
    Boolean updateByBo(ActActivityParticipantsBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<ActActivityParticipantsVo> getActivityParticipantsList(ActActivityParticipantsBo bo);

}
