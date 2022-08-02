package com.stclub.club.service;

import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.club.domain.CluCorporation;
import com.stclub.club.domain.vo.CluCorporationVo;
import com.stclub.club.domain.bo.CluCorporationBo;
import com.stclub.common.core.mybatisplus.core.IServicePlus;
    import com.stclub.common.core.page.TableDataInfo;
import com.stclub.student.domain.StuUser;
import com.stclub.student.domain.vo.StuUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 社团Service接口
 *
 * @author luo
 * @date 2022-03-09
 */
public interface ICluCorporationService extends IServicePlus<CluCorporation, CluCorporationVo> {
    /**
     * 根据新增业务对象插入社团
     * @param bo 社团新增业务对象
     * @return
     */
    Boolean insertByBo(CluCorporationBo bo);

    /**
     * 根据编辑业务对象修改社团
     * @param bo 社团编辑业务对象
     * @return
     */
    Boolean updateByBo(CluCorporationBo bo);

    /**
     * 校验并删除数据
     * @param ids 主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<CluCorporationVo> getCorporationList(CluCorporationBo bo);

    List<CluCorporation> getNewNumber();

    List<CluCorporationVo> getUserClubList(Long userId);

    List<CluCorporationVo> getUserNoInClubList(Long userId);

    List<CluCorporationVo> getUserNoInClubByCorporationList(Long userId,String corporationCode,Long typeId,String corporationName);

    List<StuUserVo> getClubPresident(Long corporationId);

    List<CluCorporationVo> getUserCorporationList(Long userId);

    List<ActActivityVo> getClubActivityList(Long corporationId);






}
