package com.stclub.activity.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.activity.domain.bo.ActActivityBo;
import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.activity.domain.ActActivity;
import com.stclub.activity.mapper.ActActivityMapper;
import com.stclub.activity.service.IActActivityService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 活动信息管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-08
 */
@Service
public class ActActivityServiceImpl extends ServicePlusImpl<ActActivityMapper, ActActivity, ActActivityVo> implements IActActivityService {

    @Override
    public Boolean insertByBo(ActActivityBo bo) {
        ActActivity add = BeanUtil.toBean(bo, ActActivity.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setActId(add.getActId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(ActActivityBo bo) {
        ActActivity update = BeanUtil.toBean(bo, ActActivity.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(ActActivity entity){
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
    public List<ActActivityVo> getList(ActActivityBo bo) {
        return baseMapper.getList(bo);
    }

    @Override
    public List<ActActivityVo> getActivityList(Long userId) {
        return baseMapper.getActivityList(userId);
    }

    @Override
    public List<ActActivityVo> getActivityListByUserId(ActActivityBo bo) {
        return baseMapper.getActivityListByUserId(bo);
    }
}
