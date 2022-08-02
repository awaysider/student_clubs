package com.stclub.ask.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.ask.domain.bo.AskIntoCorporationBo;
import com.stclub.ask.domain.vo.AskIntoCorporationVo;
import com.stclub.ask.domain.AskIntoCorporation;
import com.stclub.ask.mapper.AskIntoCorporationMapper;
import com.stclub.ask.service.IAskIntoCorporationService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户入社申请管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-09
 */
@Service
public class AskIntoCorporationServiceImpl extends ServicePlusImpl<AskIntoCorporationMapper, AskIntoCorporation, AskIntoCorporationVo> implements IAskIntoCorporationService {

    @Override
    public Boolean insertByBo(AskIntoCorporationBo bo) {
        AskIntoCorporation add = BeanUtil.toBean(bo, AskIntoCorporation.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setIntoCorporationId(add.getIntoCorporationId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(AskIntoCorporationBo bo) {
        AskIntoCorporation update = BeanUtil.toBean(bo, AskIntoCorporation.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(AskIntoCorporation entity){
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
    public List<AskIntoCorporationVo> getAskIntoCorporationList(AskIntoCorporationBo bo) {
        return baseMapper.getAskIntoCorporationList(bo);
    }

    @Override
    public List<AskIntoCorporationVo> getAskIntoCorporationListByUserId(Long userId, int status) {
        return baseMapper.getAskIntoCorporationListByUserId(userId,status);
    }
}
