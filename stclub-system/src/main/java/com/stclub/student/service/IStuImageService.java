package com.stclub.student.service;

import com.stclub.student.domain.StuImage;
import com.stclub.student.domain.vo.StuImageVo;
import com.stclub.student.domain.bo.StuImageBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 首页轮番图Service接口
 *
 * @author ruoyi
 * @date 2022-04-12
 */
public interface IStuImageService extends IServicePlus<StuImage, StuImageVo> {
    /**
     * 根据新增业务对象插入首页轮番图
     * @param bo 首页轮番图新增业务对象
     * @return
     */
    Boolean insertByBo(StuImageBo bo);

    /**
     * 根据编辑业务对象修改首页轮番图
     * @param bo 首页轮番图编辑业务对象
     * @return
     */
    Boolean updateByBo(StuImageBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
