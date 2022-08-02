package com.stclub.web.api.Information;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.stclub.activity.domain.ActActivity;
import com.stclub.activity.domain.bo.ActActivityBo;
import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.activity.service.IActActivityService;
import com.stclub.ask.domain.AskIntoCorporation;
import com.stclub.ask.domain.AskSetActivity;
import com.stclub.ask.domain.bo.AskSetActivityBo;
import com.stclub.ask.domain.vo.AskBecomePresidentVo;
import com.stclub.ask.domain.vo.AskIntoCorporationVo;
import com.stclub.ask.domain.vo.AskSetActivityVo;
import com.stclub.ask.service.IAskBecomePresidentService;
import com.stclub.ask.service.IAskIntoCorporationService;
import com.stclub.ask.service.IAskSetActivityService;
import com.stclub.club.domain.vo.CluCorporationVo;
import com.stclub.club.service.ICluCorporationService;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.core.page.TableDataInfo;
import com.stclub.common.utils.SecurityUtils;
import com.stclub.student.domain.StuImage;
import com.stclub.student.domain.StuUser;
import com.stclub.student.domain.bo.StuUserBo;
import com.stclub.student.domain.vo.StuUserVo;
import com.stclub.student.service.IStuImageService;
import com.stclub.student.service.IStuUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ljx
 * @Date 2022/3/31 0:31
 **/
@Api(value = "个人信息管理模块", tags = {"个人信息管理模块"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/userInformation")
public class UserInformationApiController extends BaseController {
    private final IStuUserService iStuUserService;
    private final ICluCorporationService iCluCorporationService;
    private final IStuImageService iStuImageService;
    private final IAskIntoCorporationService iAskIntoCorporationService;
    private final IAskBecomePresidentService iAskBecomePresidentService;
    private final IActActivityService iActActivityService;
    private final IAskSetActivityService iAskSetActivityService;
    /**
     * 用户修改个人信息
     * @author ljx
     * @param stuUserBo
     * @return
     */
    @ApiOperation(value = "用户修改个人信息",notes = "用户可以修改自己的个人信息")
    @PostMapping("/updateUser")
    public AjaxResult updateUser(@RequestBody StuUserBo stuUserBo){
        return AjaxResult.success(iStuUserService.updateByBo(stuUserBo));
    }

    @ApiOperation(value = "查看个人信息",notes = "查看个人信息")
    @GetMapping("/getUser")
    public AjaxResult getUser(
            @ApiParam(name = "userId",value = "用户ID",type = "Long")@RequestParam("userId") Long userId
    ) {
        StuUserVo stuUserVo = iStuUserService.getStuUserByUserId(userId);
        return AjaxResult.success(stuUserVo);
    }

    @ApiOperation(value = "修改个人头像",notes = "用户修改个人头像")
    @PostMapping("/updateUserLogo")
    public AjaxResult updateUserLogo(
            @ApiParam(name = "userId",value = "用户ID",type = "Long")@RequestParam("userId") Long userId,
            @ApiParam(name = "logo",value = "用户Logo",type = "String")@RequestParam("logo")String logo
    ){
        StuUser stuUser = new StuUser();
        stuUser.setLogo(logo);
        stuUser.setUserId(userId);
        boolean b = iStuUserService.updateById(stuUser);
        return toAjax(b);
    }

    @ApiOperation(value = "修改用户密码",notes = "修改密码")
    @PostMapping("/updateUserPassword")
    public AjaxResult updateUserPassword(
            @ApiParam(name = "userId",value = "用户ID",type = "Long")@RequestParam("userId") Long userId,
            @ApiParam(name = "oldPassword", value = "旧密码", type = "String")@RequestParam("oldPassword")String oldPassword,
            @ApiParam(name = "newPassword", value = "旧密码", type = "String")@RequestParam("newPassword")String newPassword
    ){
        StuUser stuUser = iStuUserService.getById(userId);
        if(!SecurityUtils.matchesPassword(oldPassword,stuUser.getPassword())){
            return new AjaxResult(203,"旧密码错误！");
        }
        stuUser.setPassword(SecurityUtils.encryptPassword(newPassword));
        boolean b = iStuUserService.updateById(stuUser);
        return toAjax(b);
    }

    @ApiOperation(value = "获取我的社团信息",notes = "获取我的社团信息")
    @GetMapping("/getUserClubList")
    public TableDataInfo getUserClubList(
            @ApiParam(name = "userId",value = "用户ID",type = "Long",required = true)@RequestParam("userId")Long userId,
            @ApiParam(value = "页码",name = "pageNum",type = "int",required = true)@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小",name = "pageSize",type = "int",required = true)@RequestParam("pageSize") int pageSize
    ) {
        PageHelper.startPage(pageNum, pageSize);
        List<CluCorporationVo> userClubList = iCluCorporationService.getUserClubList(userId);
        return getDataTable(userClubList);
    }

    @ApiOperation(value = "获取首页轮番图")
    @GetMapping("/getImage")
    public AjaxResult getImage(){
        List<StuImage> list = iStuImageService.list();
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "获取加入社团信息")
    @GetMapping("/getAskCorporationList")
    public TableDataInfo getAskCorporationList(
            @ApiParam(name = "userId",value = "用户ID",type = "Long",required = true)@RequestParam("userId")Long userId,
            @ApiParam(name = "status",value = "审核状态",type = "int",required = true)@RequestParam("status")int status,
            @ApiParam(value = "页码",name = "pageNum",type = "int",required = true)@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小",name = "pageSize",type = "int",required = true)@RequestParam("pageSize") int pageSize
    ){
        status = status-1;
        PageHelper.startPage(pageNum, pageSize);
        List<AskIntoCorporationVo> askIntoCorporationVos = iAskIntoCorporationService.getAskIntoCorporationListByUserId(userId, status);
        return getDataTable(askIntoCorporationVos);
    }

    @ApiOperation(value = "获取成为社长信息")
    @GetMapping("/getAskBecomePresidentList")
    public TableDataInfo getAskBecomePresidentList(
            @ApiParam(name = "userId",value = "用户ID",type = "Long",required = true)@RequestParam("userId")Long userId,
            @ApiParam(name = "status",value = "审核状态",type = "Long",required = true)@RequestParam("status")Long status,
            @ApiParam(value = "页码",name = "pageNum",type = "int",required = true)@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小",name = "pageSize",type = "int",required = true)@RequestParam("pageSize") int pageSize
    ){
        status = status-1;
        PageHelper.startPage(pageNum, pageSize);
        List<AskBecomePresidentVo> askBecomePresidentListByUserId = iAskBecomePresidentService.getAskBecomePresidentListByUserId(userId, status);
        return getDataTable(askBecomePresidentListByUserId);
    }

    @ApiOperation(value = "获取社团活动信息")
    @GetMapping("/getActivityList")
    public TableDataInfo getActivityList(
            @ApiParam(name = "userId",value = "用户ID",type = "Long",required = true)@RequestParam("userId")Long userId,
            @ApiParam(name = "status",value = "活动状态",type = "int",required = true)@RequestParam("status")int status,
            @ApiParam(name = "userName",value = "举办人",type = "String",required = false)@RequestParam(value = "userName",required = false)String userName,
            @ApiParam(name = "activityCode",value = "活动编码",type = "String",required = false)@RequestParam(value = "activityCode",required = false) String activityCode,
            @ApiParam(name = "corporationName",value = "社团名",type = "String",required = false)@RequestParam(value = "corporationName",required = false) String corporationName,
            @ApiParam(name = "activityName",value = "活动名",type = "String",required = false)@RequestParam(value = "activityName",required = false) String activityName,
            @ApiParam(value = "页码",name = "pageNum",type = "int",required = true)@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小",name = "pageSize",type = "int",required = true)@RequestParam("pageSize") int pageSize
    ){
        ActActivityBo actActivityBo = new ActActivityBo();
        actActivityBo.setStatus(status);
        actActivityBo.setUserId(userId);
        actActivityBo.setActivityCode(activityCode);
        actActivityBo.setCorporationName(corporationName);
        actActivityBo.setActivityName(activityName);
        actActivityBo.setUserName(userName);
        PageHelper.startPage(pageNum, pageSize);
        List<ActActivityVo> actActivityVos = iActActivityService.getActivityListByUserId(actActivityBo);
        return getDataTable(actActivityVos);
    }

    @ApiOperation(value = "获取申请活动信息")
    @GetMapping("/getAskActivityList")
    public TableDataInfo getAskActivityList(
            @ApiParam(name = "userId",value = "用户ID",type = "Long",required = true)@RequestParam("userId")Long userId,
            @ApiParam(name = "status",value = "申请状态",type = "Long",required = false)@RequestParam(value = "status",required = false)Long status,
            @ApiParam(name = "corporationName",value = "社团名",type = "String",required = false)@RequestParam(value = "corporationName",required = false) String corporationName,
            @ApiParam(name = "activityName",value = "活动名",type = "String",required = false)@RequestParam(value = "activityName",required = false) String activityName,
            @ApiParam(value = "页码",name = "pageNum",type = "int",required = true)@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "页大小",name = "pageSize",type = "int",required = true)@RequestParam("pageSize") int pageSize
    ){
        if(status != null){
            status = status-1;
        }
        AskSetActivityBo askSetActivityBo = new AskSetActivityBo();
        askSetActivityBo.setStatus(status);
        askSetActivityBo.setUserId(userId);
        askSetActivityBo.setCorporationName(corporationName);
        askSetActivityBo.setActivityName(activityName);
        PageHelper.startPage(pageNum, pageSize);
        List<AskSetActivityVo> askSetActivityList = iAskSetActivityService.getAskSetActivityList(askSetActivityBo);
        return getDataTable(askSetActivityList);
    }

    @ApiOperation(value = "修改个人基本信息")
    @PostMapping("/updateUserMsg")
    public AjaxResult updateUserMsg(
            @RequestBody StuUser stuUser
    ){
        boolean b = iStuUserService.updateById(stuUser);
        return AjaxResult.success(b);
    }




}
