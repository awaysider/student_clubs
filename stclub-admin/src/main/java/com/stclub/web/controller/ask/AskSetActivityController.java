package com.stclub.web.controller.ask;

import com.stclub.activity.domain.ActActivity;
import com.stclub.activity.domain.ActActivityParticipants;
import com.stclub.activity.service.IActActivityParticipantsService;
import com.stclub.activity.service.IActActivityService;
import com.stclub.ask.domain.AskSetActivity;
import com.stclub.ask.domain.bo.AskSetActivityBo;
import com.stclub.ask.domain.vo.AskSetActivityVo;
import com.stclub.ask.service.IAskSetActivityService;
import com.stclub.common.annotation.Log;
import com.stclub.common.annotation.RepeatSubmit;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.core.page.TableDataInfo;
import com.stclub.common.enums.BusinessType;
import com.stclub.common.utils.DateUtils;
import com.stclub.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 活动申请管理Controller
 *
 * @author luo
 * @date 2022-03-09
 */
@Validated
@Api(value = "活动申请管理控制器", tags = {"活动申请管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ask/setActivity")
public class AskSetActivityController extends BaseController {

    private final IAskSetActivityService iAskSetActivityService;

    private final IActActivityService iActActivityService;

    private final IActActivityParticipantsService iActActivityParticipantsService;

/**
 * 查询活动申请管理列表
 */
@ApiOperation("查询活动申请管理列表")
@PreAuthorize("@ss.hasPermi('ask:setActivity:list')")
@GetMapping("/list")
    public TableDataInfo list( AskSetActivityBo bo) {
        startPage();
    List<AskSetActivityVo> askSetActivityList = iAskSetActivityService.getAskSetActivityList(bo);
        return getDataTable(askSetActivityList);
    }

    /**
     * 导出活动申请管理列表
     */
    @ApiOperation("导出活动申请管理列表")
    @PreAuthorize("@ss.hasPermi('ask:setActivity:export')")
    @Log(title = "活动申请管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( AskSetActivityBo bo, HttpServletResponse response) {
        List<AskSetActivityVo> list = iAskSetActivityService.getAskSetActivityList(bo);
        ExcelUtil<AskSetActivityVo> util = new ExcelUtil<AskSetActivityVo>(AskSetActivityVo.class);
        util.exportExcel(response, list, "活动申请管理");
    }

    /**
     * 获取活动申请管理详细信息
     */
    @ApiOperation("获取活动申请管理详细信息")
    @PreAuthorize("@ss.hasPermi('ask:setActivity:query')")
    @GetMapping("/{setActivityId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("setActivityId") Long setActivityId) {
        return AjaxResult.success(iAskSetActivityService.getById(setActivityId));
    }

    /**
     * 新增活动申请管理
     */
    @ApiOperation("新增活动申请管理")
    @PreAuthorize("@ss.hasPermi('ask:setActivity:add')")
    @Log(title = "活动申请管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody AskSetActivityBo bo) {
        return toAjax(iAskSetActivityService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改活动申请管理
     */
    @ApiOperation("修改活动申请管理")
    @PreAuthorize("@ss.hasPermi('ask:setActivity:edit')")
    @Log(title = "活动申请管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody AskSetActivityBo bo) {
        return toAjax(iAskSetActivityService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除活动申请管理
     */
    @ApiOperation("删除活动申请管理")
    @PreAuthorize("@ss.hasPermi('ask:setActivity:remove')")
    @Log(title = "活动申请管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{setActivityIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] setActivityIds) {
        return toAjax(iAskSetActivityService.deleteWithValidByIds(Arrays.asList(setActivityIds), true) ? 1 : 0);
    }

    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody AskSetActivity askSetActivity){
        boolean b = iAskSetActivityService.updateById(askSetActivity);
        if (askSetActivity.getStatus()==1) {
            ActActivity actActivity = new ActActivity();
            actActivity.setEndTime(askSetActivity.getEndTime());
            actActivity.setStartTime(askSetActivity.getStartTime());
            actActivity.setActivityPlace(askSetActivity.getActivityPlace());
            actActivity.setActivityName(askSetActivity.getActivityName());
            actActivity.setActivityContent(askSetActivity.getActivityContent());
            actActivity.setActivityBudget(askSetActivity.getActivityBudget());
            actActivity.setActivityCode("ACT" + DateUtils.dateTimeNow());
            actActivity.setUserId(askSetActivity.getUserId());
            actActivity.setCorporationId(askSetActivity.getCorporationId());
            actActivity.setMaxNumber(askSetActivity.getMaxNumber());
            actActivity.setStatus(1);
            boolean save = iActActivityService.save(actActivity);
            if(save){
                ActActivityParticipants actActivityParticipants = new ActActivityParticipants();
                actActivityParticipants.setUserId(actActivity.getUserId()).setActId(actActivity.getActId());
                iActActivityParticipantsService.save(actActivityParticipants);
            }
        }
        return toAjax(b);
    }
}
