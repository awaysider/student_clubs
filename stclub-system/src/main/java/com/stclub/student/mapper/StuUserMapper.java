package com.stclub.student.mapper;

import com.stclub.student.domain.StuUser;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;
import com.stclub.student.domain.bo.StuUserBo;
import com.stclub.student.domain.vo.StuUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface StuUserMapper extends BaseMapperPlus<StuUser> {

    /**
     * 获取用户信息
     * @param bo 用户信息搜索条件
     * @return 用户信息
     */
    List<StuUserVo> getStuUserList(StuUserBo bo);

    StuUserVo getStuUserByUserId(@Param("userId")Long userId);

}
