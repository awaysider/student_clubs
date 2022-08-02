package com.stclub.ask.service;

import com.stclub.ask.domain.AskIntoInterview;
import com.stclub.ask.domain.vo.AskIntoInterviewVo;
import com.stclub.ask.domain.bo.AskIntoInterviewBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 入社面试邀请管理Service接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface IAskIntoInterviewService extends IServicePlus<AskIntoInterview, AskIntoInterviewVo> {
    /**
     * 根据新增业务对象插入入社面试邀请管理
     * @param bo 入社面试邀请管理新增业务对象
     * @return
     */
    Boolean insertByBo(AskIntoInterviewBo bo);

    /**
     * 根据编辑业务对象修改入社面试邀请管理
     * @param bo 入社面试邀请管理编辑业务对象
     * @return
     */
    Boolean updateByBo(AskIntoInterviewBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<AskIntoInterviewVo> getAskIntoInterviewList(AskIntoInterviewBo bo);
}
