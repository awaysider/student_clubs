package com.stclub.student.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.student.domain.bo.StuUserBo;
import com.stclub.student.domain.vo.StuUserVo;
import com.stclub.student.domain.StuUser;
import com.stclub.student.mapper.StuUserMapper;
import com.stclub.student.service.IStuUserService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户信息Service业务层处理
 *
 * @author luo
 * @date 2022-03-08
 */
@Service
public class StuUserServiceImpl extends ServicePlusImpl<StuUserMapper, StuUser, StuUserVo> implements IStuUserService {

    @Override
    public Boolean insertByBo(StuUserBo bo) {
        StuUser add = BeanUtil.toBean(bo, StuUser.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(StuUserBo bo) {
        StuUser update = BeanUtil.toBean(bo, StuUser.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(StuUser entity){
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
    public List<StuUserVo> getStuUserList(StuUserBo bo) {
        return baseMapper.getStuUserList(bo);
    }

    @Override
    public StuUserVo getStuUserByUserId(Long userId) {
        return baseMapper.getStuUserByUserId(userId);
    }
}
