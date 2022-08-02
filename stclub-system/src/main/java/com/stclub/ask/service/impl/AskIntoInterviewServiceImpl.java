package com.stclub.ask.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.ask.domain.bo.AskIntoInterviewBo;
import com.stclub.ask.domain.vo.AskIntoInterviewVo;
import com.stclub.ask.domain.AskIntoInterview;
import com.stclub.ask.mapper.AskIntoInterviewMapper;
import com.stclub.ask.service.IAskIntoInterviewService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 入社面试邀请管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-09
 */
@Service
public class AskIntoInterviewServiceImpl extends ServicePlusImpl<AskIntoInterviewMapper, AskIntoInterview, AskIntoInterviewVo> implements IAskIntoInterviewService {

    @Override
    public Boolean insertByBo(AskIntoInterviewBo bo) {
        AskIntoInterview add = BeanUtil.toBean(bo, AskIntoInterview.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setIntoInterviewId(add.getIntoInterviewId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(AskIntoInterviewBo bo) {
        AskIntoInterview update = BeanUtil.toBean(bo, AskIntoInterview.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(AskIntoInterview entity){
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
    public List<AskIntoInterviewVo> getAskIntoInterviewList(AskIntoInterviewBo bo) {
        return baseMapper.getAskIntoInterviewList(bo);
    }
}
