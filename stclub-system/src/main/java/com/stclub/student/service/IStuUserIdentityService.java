package com.stclub.student.service;

import com.stclub.student.domain.StuUserIdentity;
import com.stclub.student.domain.vo.StuUserIdentityVo;
import com.stclub.student.domain.bo.StuUserIdentityBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 用户身份管理Service接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface IStuUserIdentityService extends IServicePlus<StuUserIdentity, StuUserIdentityVo> {
    /**
     * 根据新增业务对象插入用户身份管理
     * @param bo 用户身份管理新增业务对象
     * @return
     */
    Boolean insertByBo(StuUserIdentityBo bo);

    /**
     * 根据编辑业务对象修改用户身份管理
     * @param bo 用户身份管理编辑业务对象
     * @return
     */
    Boolean updateByBo(StuUserIdentityBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获取用户身份信息
     * @param bo 获取条件
     * @return 用户身份信息
     */
    List<StuUserIdentityVo> getStuUserIdentityList(StuUserIdentityBo bo);

    List<StuUserIdentityVo> getStuUserIdentityListByCorporationId(@Param("corporationId")Long corporationId);

}
