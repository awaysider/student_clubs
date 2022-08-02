package com.stclub.student.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.student.domain.bo.StuCollegeBo;
import com.stclub.student.domain.vo.StuCollegeVo;
import com.stclub.student.domain.StuCollege;
import com.stclub.student.mapper.StuCollegeMapper;
import com.stclub.student.service.IStuCollegeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 学院管理Service业务层处理
 *
 * @author luo
 * @date 2022-03-08
 */
@Service
public class StuCollegeServiceImpl extends ServicePlusImpl<StuCollegeMapper, StuCollege, StuCollegeVo> implements IStuCollegeService {

    @Override
    public Boolean insertByBo(StuCollegeBo bo) {
        StuCollege add = BeanUtil.toBean(bo, StuCollege.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setCollegeId(add.getCollegeId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(StuCollegeBo bo) {
        StuCollege update = BeanUtil.toBean(bo, StuCollege.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(StuCollege entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}
