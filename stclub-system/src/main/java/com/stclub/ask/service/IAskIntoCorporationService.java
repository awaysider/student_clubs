package com.stclub.ask.service;

import com.stclub.ask.domain.AskIntoCorporation;
import com.stclub.ask.domain.vo.AskIntoCorporationVo;
import com.stclub.ask.domain.bo.AskIntoCorporationBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 用户入社申请管理Service接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface IAskIntoCorporationService extends IServicePlus<AskIntoCorporation, AskIntoCorporationVo> {
    /**
     * 根据新增业务对象插入用户入社申请管理
     * @param bo 用户入社申请管理新增业务对象
     * @return
     */
    Boolean insertByBo(AskIntoCorporationBo bo);

    /**
     * 根据编辑业务对象修改用户入社申请管理
     * @param bo 用户入社申请管理编辑业务对象
     * @return
     */
    Boolean updateByBo(AskIntoCorporationBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<AskIntoCorporationVo> getAskIntoCorporationList(AskIntoCorporationBo bo);

    List<AskIntoCorporationVo> getAskIntoCorporationListByUserId(Long userId,int status);
}
