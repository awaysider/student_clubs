package com.stclub.web.api.Information;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stclub.ask.domain.AskBecomePresident;
import com.stclub.ask.domain.AskSetActivity;
import com.stclub.ask.domain.vo.AskBecomePresidentVo;
import com.stclub.ask.service.IAskBecomePresidentService;
import com.stclub.ask.service.IAskSetActivityService;
import com.stclub.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ljx
 * @Date 2022/4/4 1:07
 **/
@Api(value = "申请模块", tags = {"申请模块"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/application")
public class ApplicationApiController {
    private final IAskSetActivityService iAskSetActivityService;
    private final IAskBecomePresidentService iAskBecomePresidentService;

    @ApiOperation(value = "活动申请模块",notes = "只有社长才有资格申请活动")
    @PostMapping("/addSetActivity")
    public AjaxResult addSetActivity(
            @ApiParam(value = "用户ID",name = "userId")@RequestParam("userId")Long userId,
            @ApiParam(value = "社团ID",name = "corporationId")@RequestParam("corporationId")Long corporationId,
            @ApiParam(value = "活动名",name = "activityName")@RequestParam("activityName")String activityName,
            @ApiParam(value = "活动预算",name = "activityBudget")@RequestParam("activityBudget")String activityBudget,
            @ApiParam(value = "活动人数",name = "maxNumber")@RequestParam("maxNumber")Long maxNumber,
            @ApiParam(value = "活动开始时间",name = "startTime")@RequestParam("startTime")String startTime,
            @ApiParam(value = "活动结束时间",name = "endTime")@RequestParam("endTime")String endTime,
            @ApiParam(value = "活动地点",name = "activityPlace")@RequestParam("activityPlace")String activityPlace,
            @ApiParam(value = "活动内容",name = "activityContent")@RequestParam("activityContent")String activityContent
    ) throws ParseException {
        AskSetActivity askSetActivity = new AskSetActivity();
        askSetActivity.setUserId(userId);
        askSetActivity.setCorporationId(corporationId);
        askSetActivity.setActivityName(activityName);
        askSetActivity.setActivityBudget(activityBudget);
        askSetActivity.setMaxNumber(maxNumber);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        askSetActivity.setStartTime(format.parse(startTime));
        askSetActivity.setEndTime(format.parse(endTime));
        askSetActivity.setActivityPlace(activityPlace);
        askSetActivity.setActivityContent(activityContent);
        askSetActivity.setCreateTime(new Date());
        askSetActivity.setStatus((long)0);
        boolean save = iAskSetActivityService.save(askSetActivity);
        return AjaxResult.success(save);
    }

    @ApiOperation(value = "成员变成社长申请",notes = "成员变成社长申请")
    @PostMapping("/addBecomePresident")
    public AjaxResult addBecomePresident(
            @RequestBody AskBecomePresident askBecomePresident
    ){
        List<AskBecomePresident> list = iAskBecomePresidentService.list(new LambdaQueryWrapper<AskBecomePresident>()
                .eq(AskBecomePresident::getCorporationId, askBecomePresident.getCorporationId())
                .eq(AskBecomePresident::getUserId, askBecomePresident.getUserId())
                .notIn(AskBecomePresident::getStatus, 2));
        if (list.size() == 0) {
            askBecomePresident.setStatus((long) 0);
            boolean save = iAskBecomePresidentService.save(askBecomePresident);
            return AjaxResult.success(save);
        }
        return AjaxResult.error("请勿重复申请！");

    }
}
