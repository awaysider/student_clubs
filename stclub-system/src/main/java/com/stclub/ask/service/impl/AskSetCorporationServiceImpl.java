package com.stclub.ask.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.ask.domain.bo.AskSetCorporationBo;
import com.stclub.ask.domain.vo.AskSetCorporationVo;
import com.stclub.ask.domain.AskSetCorporation;
import com.stclub.ask.mapper.AskSetCorporationMapper;
import com.stclub.ask.service.IAskSetCorporationService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 成员创建社团申请管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-09
 */
@Service
public class AskSetCorporationServiceImpl extends ServicePlusImpl<AskSetCorporationMapper, AskSetCorporation, AskSetCorporationVo> implements IAskSetCorporationService {

    @Override
    public Boolean insertByBo(AskSetCorporationBo bo) {
        AskSetCorporation add = BeanUtil.toBean(bo, AskSetCorporation.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setSetCorporationId(add.getSetCorporationId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(AskSetCorporationBo bo) {
        AskSetCorporation update = BeanUtil.toBean(bo, AskSetCorporation.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(AskSetCorporation entity){
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
    public List<AskSetCorporationVo> getAskSetCorporationList(AskSetCorporationBo bo) {
        return baseMapper.getAskSetCorporationList(bo);
    }
}
