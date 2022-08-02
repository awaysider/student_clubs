package com.stclub.activity.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.activity.domain.bo.ActActivityParticipantsBo;
import com.stclub.activity.domain.vo.ActActivityParticipantsVo;
import com.stclub.activity.domain.ActActivityParticipants;
import com.stclub.activity.mapper.ActActivityParticipantsMapper;
import com.stclub.activity.service.IActActivityParticipantsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 活动参加人员管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-08
 */
@Service
public class ActActivityParticipantsServiceImpl extends ServicePlusImpl<ActActivityParticipantsMapper, ActActivityParticipants, ActActivityParticipantsVo> implements IActActivityParticipantsService {

    @Override
    public Boolean insertByBo(ActActivityParticipantsBo bo) {
        ActActivityParticipants add = BeanUtil.toBean(bo, ActActivityParticipants.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setParticipantsId(add.getParticipantsId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(ActActivityParticipantsBo bo) {
        ActActivityParticipants update = BeanUtil.toBean(bo, ActActivityParticipants.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(ActActivityParticipants entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }

    @Override
    public List<ActActivityParticipantsVo> getActivityParticipantsList(ActActivityParticipantsBo bo) {
        return baseMapper.getActivityParticipantsList(bo);
    }
}
