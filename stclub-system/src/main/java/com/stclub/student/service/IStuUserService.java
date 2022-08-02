package com.stclub.student.service;

import com.stclub.student.domain.StuUser;
import com.stclub.student.domain.vo.StuUserVo;
import com.stclub.student.domain.bo.StuUserBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface IStuUserService extends IServicePlus<StuUser, StuUserVo> {
    /**
     * 根据新增业务对象插入用户信息
     * @param bo 用户信息新增业务对象
     * @return
     */
    Boolean insertByBo(StuUserBo bo);

    /**
     * 根据编辑业务对象修改用户信息
     * @param bo 用户信息编辑业务对象
     * @return
     */
    Boolean updateByBo(StuUserBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获取用户信息
     * @param bo 用户信息搜索条件
     * @return 用户信息
     */
    List<StuUserVo> getStuUserList(StuUserBo bo);

    StuUserVo getStuUserByUserId(@Param("userId")Long userId);
}
