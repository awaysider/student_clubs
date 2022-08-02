package com.stclub.activity.mapper;

import com.stclub.activity.domain.ActActivity;
import com.stclub.activity.domain.bo.ActActivityBo;
import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.spi.LocaleServiceProvider;

/**
 * 活动信息管理Mapper接口
 *
 * @author luo
 * @date 2022-03-08
 */
public interface ActActivityMapper extends BaseMapperPlus<ActActivity> {

    List<ActActivityVo> getList(ActActivityBo bo);

    List<ActActivityVo> getActivityList(@Param("userId")Long userId);

    List<ActActivityVo> getActivityListByUserId(ActActivityBo bo);
}
