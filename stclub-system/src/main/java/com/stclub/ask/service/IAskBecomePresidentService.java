package com.stclub.ask.service;

import com.stclub.ask.domain.AskBecomePresident;
import com.stclub.ask.domain.vo.AskBecomePresidentVo;
import com.stclub.ask.domain.bo.AskBecomePresidentBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 成员成为社长申请管理Service接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface IAskBecomePresidentService extends IServicePlus<AskBecomePresident, AskBecomePresidentVo> {
    /**
     * 根据新增业务对象插入成员成为社长申请管理
     * @param bo 成员成为社长申请管理新增业务对象
     * @return
     */
    Boolean insertByBo(AskBecomePresidentBo bo);

    /**
     * 根据编辑业务对象修改成员成为社长申请管理
     * @param bo 成员成为社长申请管理编辑业务对象
     * @return
     */
    Boolean updateByBo(AskBecomePresidentBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<AskBecomePresidentVo> getAskBecomePresidentList(AskBecomePresidentBo bo);

    List<AskBecomePresidentVo> getAskBecomePresidentListByUserId(Long userId, long status);

}
