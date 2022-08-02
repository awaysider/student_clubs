package com.stclub.student.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.student.domain.bo.StuImageBo;
import com.stclub.student.domain.vo.StuImageVo;
import com.stclub.student.domain.StuImage;
import com.stclub.student.mapper.StuImageMapper;
import com.stclub.student.service.IStuImageService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 首页轮番图Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-12
 */
@Service
public class StuImageServiceImpl extends ServicePlusImpl<StuImageMapper, StuImage, StuImageVo> implements IStuImageService {

    @Override
    public Boolean insertByBo(StuImageBo bo) {
        StuImage add = BeanUtil.toBean(bo, StuImage.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setImageId(add.getImageId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(StuImageBo bo) {
        StuImage update = BeanUtil.toBean(bo, StuImage.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(StuImage entity){
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
