package com.stclub.web.api.Information;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.ask.domain.AskIntoCorporation;
import com.stclub.ask.domain.vo.AskBecomePresidentVo;
import com.stclub.ask.domain.vo.AskSetActivityVo;
import com.stclub.ask.service.IAskIntoCorporationService;
import com.stclub.club.domain.CluCorporation;
import com.stclub.club.domain.CluCorporationType;
import com.stclub.club.domain.vo.CluCorporationVo;
import com.stclub.club.service.ICluCorporationService;
import com.stclub.club.service.ICluCorporationTypeService;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.core.page.TableDataInfo;
import com.stclub.student.domain.StuUser;
import com.stclub.student.domain.StuUserIdentity;
import com.stclub.student.service.IStuUserIdentityService;
import com.stclub.student.service.IStuUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 社团模块
 * @author ljx
 * @Date 2022/4/4 0:20
 **/
@Api(value = "社团模块", tags = {"社团模块"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/corporation")
public class CluCorporationApiController extends BaseController {
    private final ICluCorporationService iCluCorporationService;
    private final IStuUserService iStuUserService;
    private final IStuUserIdentityService iStuUserIdentityService;
    private final ICluCorporationTypeService iCluCorporationTypeService;
    private final IAskIntoCorporationService iAskIntoCorporationService;

    @ApiOperation(value = "获取社团信息",notes = "根据社团ID来获取社团信息")
    @GetMapping("/getCluCorporation")
    public AjaxResult getCluCorporation(
            @ApiParam(value = "社团ID",name = "corporationId",type = "Long")@RequestParam("corporationId")Long corporationId,
            @ApiParam(value = "用户ID",name = "userId",type = "Long")@RequestParam("userId")Long userId
    ){
        CluCorporation cluCorporation = iCluCorporationService.getById(corporationId);
        StuUser stuUser = iStuUserService.getById(userId);
        AskSetActivityVo askSetActivityVo = new AskSetActivityVo();
        askSetActivityVo.setUserId(userId);
        askSetActivityVo.setUserName(stuUser.getUserName());
        askSetActivityVo.setCorporationId(corporationId);
        askSetActivityVo.setCorporationName(cluCorporation.getCorporationName());
        return AjaxResult.success(askSetActivityVo);
    }

    @ApiOperation(value = "获取成为社长信息",notes = "根据社团ID来获取社团信息")
    @GetMapping("/getBecomePresident")
    public AjaxResult getBecomePresident(
            @ApiParam(value = "社团ID",name = "corporationId",type = "Long")@RequestParam("corporationId")Long corporationId,
            @ApiParam(value = "用户ID",name = "userId",type = "Long")@RequestParam("userId")Long userId
    ){
        CluCorporation cluCorporation = iCluCorporationService.getById(corporationId);
        StuUser stuUser = iStuUserService.getById(userId);
        AskBecomePresidentVo askBecomePresidentVo = new AskBecomePresidentVo();
        StuUserIdentity userIdentity = iStuUserIdentityService.getOne(new LambdaQueryWrapper<StuUserIdentity>().eq(StuUserIdentity::getCorporationId, corporationId).eq(StuUserIdentity::getUserStatus, 0));
        StuUser userServiceById = iStuUserService.getById(userIdentity.getUserId());
        askBecomePresidentVo.setCorporationId(corporationId);
        askBecomePresidentVo.setCorporationName(cluCorporation.getCorporationName());
        askBecomePresidentVo.setOriginalUserId(userIdentity.getUserId());
        askBecomePresidentVo.setOriginalUserName(userServiceById.getUserName());
        askBecomePresidentVo.setUserId(userId);
        askBecomePresidentVo.setUserName(stuUser.getUserName());
        return AjaxResult.success(askBecomePresidentVo);
    }

    @ApiOperation(value = "用户获取不在的社团列表",notes = "根据用户ID来获取用户不在的社团")
    @GetMapping("/getUserNoInClubList")
    public AjaxResult getUserNoInClubList(
            @ApiParam(value = "用户ID",name = "userId",type = "Long")@RequestParam("userId")Long userId
    ){
        List<CluCorporationVo> userNoInClubList = iCluCorporationService.getUserNoInClubList(userId);
        return AjaxResult.success(userNoInClubList);
    }

    @ApiOperation(value = "获取不在的社团列表",notes = "用户可根据不同条件来获取社团列表")
    @PostMapping("/getUserNoInClubByCorporationList")
    public TableDataInfo getUserNoInClubByCorporationList(
            @ApiParam(value = "用户ID",name = "userId",type = "Long")@RequestParam("userId")Long userId,
            @ApiParam(value = "社团编号",name = "corporationCode",type = "String",required = false)@RequestParam(name = "corporationCode",required = false)String corporationCode,
            @ApiParam(value = "社团类型ID",name = "typeId",type = "Long",required = false)@RequestParam(name = "typeId",required = false)Long typeId,
            @ApiParam(value = "社团名",name = "corporationName",type = "String",required = false)@RequestParam(name = "corporationName",required = false)String corporationName,
            @ApiParam(value = "页码",name = "pageNum",type = "int",required = true)@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小",name = "pageSize",type = "int",required = true)@RequestParam("pageSize") int pageSize

    ){
        PageHelper.startPage(pageNum, pageSize);
        List<CluCorporationVo> userNoInClubByCorporationList = iCluCorporationService.getUserNoInClubByCorporationList(userId, corporationCode, typeId, corporationName);
        return getDataTable(userNoInClubByCorporationList);
    }

    @ApiOperation(value = "获取所有的社团类型")
    @GetMapping("/getClubTypeList")
    public AjaxResult getClubTypeList(){
        List<CluCorporationType> list = iCluCorporationTypeService.list();
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "根据社团获取社长信息")
    @GetMapping("/getClubPresident")
    public AjaxResult getClubPresident(
            @ApiParam(value = "社团Id",name = "corporationId",type = "Long")@RequestParam("corporationId")Long corporationId
    ){
        return AjaxResult.success(iCluCorporationService.getClubPresident(corporationId));
    }

    @ApiOperation(value = "申请加入社团")
    @PostMapping("/addIntoCorporation")
    public AjaxResult addIntoCorporation(
            @RequestBody AskIntoCorporation askIntoCorporation
    ){
        List<AskIntoCorporation> one = iAskIntoCorporationService.list(new LambdaQueryWrapper<AskIntoCorporation>()
                .eq(AskIntoCorporation::getCorporationId, askIntoCorporation.getCorporationId())
                .eq(AskIntoCorporation::getUserId, askIntoCorporation.getUserId())
                .notIn(AskIntoCorporation::getStatus,2)
        );
        if(one.size() == 0) {
            askIntoCorporation.setStatus(0);
            boolean save = iAskIntoCorporationService.save(askIntoCorporation);
            return AjaxResult.success(save);
        }
        return AjaxResult.error("请勿重复申请");
    }

    @ApiOperation(value = "用户获取参加的社团信息")
    @GetMapping("/getUserCorporationList")
    public AjaxResult getUserCorporationList(
            @RequestParam("userId")Long userId
    ){
        List<CluCorporationVo> userCorporationList = iCluCorporationService.getUserCorporationList(userId);
        return AjaxResult.success(userCorporationList);
    }

    @ApiOperation(value = "获取社团的活动信息")
    @GetMapping("/getClubActivityList")
    public TableDataInfo getClubActivityList(
            @ApiParam(value = "社团Id",name = "corporationId",type = "Long")@RequestParam("corporationId")Long corporationId,
            @ApiParam(value = "页码",name = "pageNum",type = "int",required = true)@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小",name = "pageSize",type = "int",required = true)@RequestParam("pageSize") int pageSize
    ){
        PageHelper.startPage(pageNum, pageSize);
        List<ActActivityVo> clubActivityList = iCluCorporationService.getClubActivityList(corporationId);
        return getDataTable(clubActivityList);
    }


}
