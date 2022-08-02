package com.stclub.student.service;

import com.stclub.student.domain.StuCollege;
import com.stclub.student.domain.vo.StuCollegeVo;
import com.stclub.student.domain.bo.StuCollegeBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 学院管理Service接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface IStuCollegeService extends IServicePlus<StuCollege, StuCollegeVo> {
    /**
     * 根据新增业务对象插入学院管理
     * @param bo 学院管理新增业务对象
     * @return
     */
    Boolean insertByBo(StuCollegeBo bo);

    /**
     * 根据编辑业务对象修改学院管理
     * @param bo 学院管理编辑业务对象
     * @return
     */
    Boolean updateByBo(StuCollegeBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
