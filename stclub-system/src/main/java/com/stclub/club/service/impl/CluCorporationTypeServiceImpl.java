package com.stclub.club.service.impl;

import cn.hutool.core.bean.BeanUtil;
    import com.stclub.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.stclub.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stclub.common.utils.StringUtils;
import com.stclub.club.domain.bo.CluCorporationTypeBo;
import com.stclub.club.domain.vo.CluCorporationTypeVo;
import com.stclub.club.domain.CluCorporationType;
import com.stclub.club.mapper.CluCorporationTypeMapper;
import com.stclub.club.service.ICluCorporationTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 社团类型Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-09
 */
@Service
public class CluCorporationTypeServiceImpl extends ServicePlusImpl<CluCorporationTypeMapper, CluCorporationType, CluCorporationTypeVo> implements ICluCorporationTypeService {

    @Override
    public Boolean insertByBo(CluCorporationTypeBo bo) {
        CluCorporationType add = BeanUtil.toBean(bo, CluCorporationType.class);
        validEntityBeforeSave(add);
        boolean flag = save(add);
        if (flag) {
            bo.setCoporationTypeId(add.getCoporationTypeId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(CluCorporationTypeBo bo) {
        CluCorporationType update = BeanUtil.toBean(bo, CluCorporationType.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(CluCorporationType entity){
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
