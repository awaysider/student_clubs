package com.stclub.activity.service;

import com.stclub.activity.domain.ActActivity;
import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.activity.domain.bo.ActActivityBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 活动信息管理Service接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface IActActivityService extends IServicePlus<ActActivity, ActActivityVo> {
    /**
     * 根据新增业务对象插入活动信息管理
     * @param bo 活动信息管理新增业务对象
     * @return
     */
    Boolean insertByBo(ActActivityBo bo);

    /**
     * 根据编辑业务对象修改活动信息管理
     * @param bo 活动信息管理编辑业务对象
     * @return
     */
    Boolean updateByBo(ActActivityBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<ActActivityVo> getList(ActActivityBo bo);

    List<ActActivityVo> getActivityList(Long userId);

    List<ActActivityVo> getActivityListByUserId(ActActivityBo bo);
}
