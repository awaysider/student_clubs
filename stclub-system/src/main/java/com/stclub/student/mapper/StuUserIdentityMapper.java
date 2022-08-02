package com.stclub.student.mapper;

import com.stclub.student.domain.StuUserIdentity;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;
import com.stclub.student.domain.bo.StuUserIdentityBo;
import com.stclub.student.domain.vo.StuUserIdentityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户身份管理Mapper接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface StuUserIdentityMapper extends BaseMapperPlus<StuUserIdentity> {

    /**
     * 获取用户身份信息
     * @param bo 获取条件
     * @return 用户身份信息
     */
    List<StuUserIdentityVo> getStuUserIdentityList(StuUserIdentityBo bo);

    List<StuUserIdentityVo> getStuUserIdentityListByCorporationId(@Param("corporationId")Long corporationId);

}
