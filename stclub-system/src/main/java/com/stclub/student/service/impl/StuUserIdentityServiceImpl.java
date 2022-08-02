package com.stclub.student.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.student.domain.bo.StuUserIdentityBo;
import com.stclub.student.domain.vo.StuUserIdentityVo;
import com.stclub.student.domain.StuUserIdentity;
import com.stclub.student.mapper.StuUserIdentityMapper;
import com.stclub.student.service.IStuUserIdentityService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户身份管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-08
 */
@Service
public class StuUserIdentityServiceImpl extends ServicePlusImpl<StuUserIdentityMapper, StuUserIdentity, StuUserIdentityVo> implements IStuUserIdentityService {

    @Override
    public Boolean insertByBo(StuUserIdentityBo bo) {
        StuUserIdentity add = BeanUtil.toBean(bo, StuUserIdentity.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setIdentityId(add.getIdentityId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(StuUserIdentityBo bo) {
        StuUserIdentity update = BeanUtil.toBean(bo, StuUserIdentity.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(StuUserIdentity entity){
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
    public List<StuUserIdentityVo> getStuUserIdentityList(StuUserIdentityBo bo) {
        return baseMapper.getStuUserIdentityList(bo);
    }

    @Override
    public List<StuUserIdentityVo> getStuUserIdentityListByCorporationId(Long corporationId) {
        return baseMapper.getStuUserIdentityListByCorporationId(corporationId);
    }
}
