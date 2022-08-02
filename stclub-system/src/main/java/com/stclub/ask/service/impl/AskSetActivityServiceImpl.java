package com.stclub.ask.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.ask.domain.bo.AskSetActivityBo;
import com.stclub.ask.domain.vo.AskSetActivityVo;
import com.stclub.ask.domain.AskSetActivity;
import com.stclub.ask.mapper.AskSetActivityMapper;
import com.stclub.ask.service.IAskSetActivityService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 活动申请管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-09
 */
@Service
public class AskSetActivityServiceImpl extends ServicePlusImpl<AskSetActivityMapper, AskSetActivity, AskSetActivityVo> implements IAskSetActivityService {

    @Override
    public Boolean insertByBo(AskSetActivityBo bo) {
        AskSetActivity add = BeanUtil.toBean(bo, AskSetActivity.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setSetActivityId(add.getSetActivityId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(AskSetActivityBo bo) {
        AskSetActivity update = BeanUtil.toBean(bo, AskSetActivity.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(AskSetActivity entity){
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
    public List<AskSetActivityVo> getAskSetActivityList(AskSetActivityBo bo) {
        return baseMapper.getAskSetActivityList(bo);
    }
}
