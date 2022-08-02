package com.stclub.club.mapper;

import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.club.domain.CluCorporation;
import com.stclub.club.domain.bo.CluCorporationBo;
import com.stclub.club.domain.vo.CluCorporationVo;
import com.stclub.common.core.mybatisplus.core.BaseMapperPlus;
import com.stclub.student.domain.StuUser;
import com.stclub.student.domain.vo.StuUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 社团Mapper接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface CluCorporationMapper extends BaseMapperPlus<CluCorporation> {

    List<CluCorporationVo> getCorporationList(CluCorporationBo bo);
    List<CluCorporation> getNewNumber();
    List<CluCorporationVo> getUserClubList(@Param("userId")Long userId);
    List<CluCorporationVo> getUserNoInClubList(@Param("userId")Long userId);
    List<CluCorporationVo> getUserNoInClubByCorporationList(@Param("userId")Long userId,@Param("corporationCode")String corporationCode,@Param("typeId")Long typeId,@Param("corporationName")String corporationName);
    List<StuUserVo> getClubPresident(@Param("corporationId")Long corporationId);
    List<CluCorporationVo> getUserCorporationList(@Param("userId")Long userId);
    List<ActActivityVo> getClubActivityList(@Param("corporationId")Long corporationId);



}
