package com.stclub.club.service;

import com.stclub.club.domain.CluCorporationType;
import com.stclub.club.domain.vo.CluCorporationTypeVo;
import com.stclub.club.domain.bo.CluCorporationTypeBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 社团类型Service接口
 *
 * @author ruoyi
 * @date 2022-03-09
 */
public interface ICluCorporationTypeService extends IServicePlus<CluCorporationType, CluCorporationTypeVo> {
    /**
     * 根据新增业务对象插入社团类型
     * @param bo 社团类型新增业务对象
     * @return
     */
    Boolean insertByBo(CluCorporationTypeBo bo);

    /**
     * 根据编辑业务对象修改社团类型
     * @param bo 社团类型编辑业务对象
     * @return
     */
    Boolean updateByBo(CluCorporationTypeBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
