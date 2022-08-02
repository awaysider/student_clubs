package com.stclub.club.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.common.core.page.TableDataInfo;
import com.stclub.student.domain.StuUser;
import com.stclub.student.domain.vo.StuUserVo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.club.domain.bo.CluCorporationBo;
import com.stclub.club.domain.vo.CluCorporationVo;
import com.stclub.club.domain.CluCorporation;
import com.stclub.club.mapper.CluCorporationMapper;
import com.stclub.club.service.ICluCorporationService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 社团Service业务层处理
 *
 * @author luo
 * @date 2022-03-09
 */
@Service
public class CluCorporationServiceImpl extends ServicePlusImpl<CluCorporationMapper, CluCorporation, CluCorporationVo> implements ICluCorporationService {

    @Override
    public Boolean insertByBo(CluCorporationBo bo) {
        CluCorporation add = BeanUtil.toBean(bo, CluCorporation.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setCorporationId(add.getCorporationId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(CluCorporationBo bo) {
        CluCorporation update = BeanUtil.toBean(bo, CluCorporation.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(CluCorporation entity){
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
    public List<CluCorporationVo> getCorporationList(CluCorporationBo bo) {
        return baseMapper.getCorporationList(bo);
    }

    @Override
    public List<CluCorporation> getNewNumber() {
        return baseMapper.getNewNumber();
    }

    @Override
    public List<CluCorporationVo> getUserClubList(Long userId) {
        return baseMapper.getUserClubList(userId);
    }

    @Override
    public List<CluCorporationVo> getUserNoInClubList(Long userId) {
        return baseMapper.getUserNoInClubList(userId);
    }

    @Override
    public List<CluCorporationVo> getUserNoInClubByCorporationList(Long userId, String corporationCode, Long typeId, String corporationName) {
        return baseMapper.getUserNoInClubByCorporationList(userId,corporationCode,typeId,corporationName);
    }

    @Override
    public List<StuUserVo> getClubPresident(Long corporationId) {
        return baseMapper.getClubPresident(corporationId);
    }

    @Override
    public List<CluCorporationVo> getUserCorporationList(Long userId) {
        return baseMapper.getUserCorporationList(userId);
    }

    @Override
    public List<ActActivityVo> getClubActivityList(Long corporationId) {
        return baseMapper.getClubActivityList(corporationId);
    }
}
