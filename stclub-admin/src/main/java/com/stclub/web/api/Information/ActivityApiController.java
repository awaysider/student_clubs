package com.stclub.web.api.Information;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.stclub.activity.domain.ActActivity;
import com.stclub.activity.domain.ActActivityParticipants;
import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.activity.service.IActActivityParticipantsService;
import com.stclub.activity.service.IActActivityService;
import com.stclub.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ljx
 * @Date 2022/3/31 1:08
 **/
@Api(value = "活动信息模块", tags = {"活动信息模块"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/activity")
public class ActivityApiController {
    private final IActActivityService iActActivityService;
    private final IActActivityParticipantsService iActActivityParticipantsService;

    @ApiOperation(value = "获取本社团的活动信息",notes = "")
    @GetMapping("/getActivityList")
    public AjaxResult getActivityList(
            @ApiParam(name = "userId",value = "用户ID",type = "Long")@RequestParam("userId") Long userId
    ){
        List<ActActivityVo> activityList = iActActivityService.getActivityList(userId);
        return AjaxResult.success(activityList);
    }

    @PostMapping("/addUserActivity")
    public AjaxResult addUserActivity(
            @ApiParam(name = "userId",value = "用户ID",type = "Long")@RequestParam("userId") Long userId,
            @ApiParam(name = "actId",value = "活动Id",type = "Long")@RequestParam("actId") Long actId
    ){
        ActActivityParticipants one = iActActivityParticipantsService.getOne(new LambdaQueryWrapper<ActActivityParticipants>()
                .eq(ActActivityParticipants::getActId, actId)
                .eq(ActActivityParticipants::getUserId, userId));
        if(one == null){
            List<ActActivityParticipants> list = iActActivityParticipantsService.list(new LambdaQueryWrapper<ActActivityParticipants>().eq(ActActivityParticipants::getActId, actId));
            ActActivity byId = iActActivityService.getById(actId);
            if(list.size() <byId.getMaxNumber()){
                ActActivityParticipants actActivityParticipants = new ActActivityParticipants();
                actActivityParticipants.setActId(actId);
                actActivityParticipants.setUserId(userId);
                boolean save = iActActivityParticipantsService.save(actActivityParticipants);
                if (save){
                    return AjaxResult.success("加入成功！");
                }
                return AjaxResult.error("加入失败，请重新尝试");
            }
            return AjaxResult.error("人数已达上限");
        }
        return AjaxResult.error("你已参加活动，请勿重复申请！");
    }


}
