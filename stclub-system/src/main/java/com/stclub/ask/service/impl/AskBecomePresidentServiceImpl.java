package com.stclub.ask.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.ask.domain.bo.AskBecomePresidentBo;
import com.stclub.ask.domain.vo.AskBecomePresidentVo;
import com.stclub.ask.domain.AskBecomePresident;
import com.stclub.ask.mapper.AskBecomePresidentMapper;
import com.stclub.ask.service.IAskBecomePresidentService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 成员成为社长申请管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-09
 */
@Service
public class AskBecomePresidentServiceImpl extends ServicePlusImpl<AskBecomePresidentMapper, AskBecomePresident, AskBecomePresidentVo> implements IAskBecomePresidentService {

    @Override
    public Boolean insertByBo(AskBecomePresidentBo bo) {
        AskBecomePresident add = BeanUtil.toBean(bo, AskBecomePresident.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setPresidentId(add.getPresidentId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(AskBecomePresidentBo bo) {
        AskBecomePresident update = BeanUtil.toBean(bo, AskBecomePresident.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(AskBecomePresident entity){
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
    public List<AskBecomePresidentVo> getAskBecomePresidentList(AskBecomePresidentBo bo) {
        return baseMapper.getAskBecomePresidentList(bo);
    }

    @Override
    public List<AskBecomePresidentVo> getAskBecomePresidentListByUserId(Long userId, long status) {
        return baseMapper.getAskBecomePresidentListByUserId(userId,status);
    }
}
