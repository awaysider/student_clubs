package com.stclub.web.controller.activity;

import java.util.List;
import java.util.Arrays;

import com.github.pagehelper.PageHelper;
import com.stclub.activity.domain.ActActivityParticipants;
import com.stclub.activity.domain.vo.ActActivityVo;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.stclub.common.annotation.RepeatSubmit;
import com.stclub.common.annotation.Log;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.enums.BusinessType;
import com.stclub.common.utils.poi.ExcelUtil;
import com.stclub.activity.domain.vo.ActActivityParticipantsVo;
import com.stclub.activity.domain.bo.ActActivityParticipantsBo;
import com.stclub.activity.service.IActActivityParticipantsService;
    import com.stclub.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 活动参加人员管理Controller
 *
 * @author luo
 * @date 2022-03-08
 */
@Validated
@Api(value = "活动参加人员管理控制器", tags = {"活动参加人员管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/activity/activityParticipants")
public class ActActivityParticipantsController extends BaseController {

    private final IActActivityParticipantsService iActActivityParticipantsService;

    /**
     * 查询活动参加人员管理列表
     */
    @ApiOperation("查询活动参加人员管理列表")
    @PreAuthorize("@ss.hasPermi('activity:activityParticipants:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActActivityParticipantsBo bo) {
        startPage();
        List<ActActivityParticipantsVo> list = iActActivityParticipantsService.getActivityParticipantsList(bo);
        return getDataTable(list);
    }

    /**
     * 导出活动参加人员管理列表
     */
    @ApiOperation("导出活动参加人员管理列表")
    @PreAuthorize("@ss.hasPermi('activity:activityParticipants:export')")
    @Log(title = "活动参加人员管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated ActActivityParticipantsBo bo, HttpServletResponse response) {
        List<ActActivityParticipantsVo> list = iActActivityParticipantsService.getActivityParticipantsList(bo);;
        ExcelUtil<ActActivityParticipantsVo> util = new ExcelUtil<ActActivityParticipantsVo>(ActActivityParticipantsVo.class);
        util.exportExcel(response, list, "活动信息管理");
    }

    /**
     * 获取活动参加人员管理详细信息
     */
    @ApiOperation("获取活动参加人员管理详细信息")
    @PreAuthorize("@ss.hasPermi('activity:activityParticipants:query')")
    @GetMapping("/{participantsId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("participantsId") Long participantsId) {
        return AjaxResult.success(iActActivityParticipantsService.getById(participantsId));
    }

    /**
     * 新增活动参加人员管理
     */
    @ApiOperation("新增活动参加人员管理")
    @PreAuthorize("@ss.hasPermi('activity:activityParticipants:add')")
    @Log(title = "活动参加人员管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody ActActivityParticipantsBo bo) {
        return toAjax(iActActivityParticipantsService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改活动参加人员管理
     */
    @ApiOperation("修改活动参加人员管理")
    @PreAuthorize("@ss.hasPermi('activity:activityParticipants:edit')")
    @Log(title = "活动参加人员管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@RequestBody ActActivityParticipantsBo bo) {
        return toAjax(iActActivityParticipantsService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除活动参加人员管理
     */
    @ApiOperation("删除活动参加人员管理")
    @PreAuthorize("@ss.hasPermi('activity:activityParticipants:remove')")
    @Log(title = "活动参加人员管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{participantsIds}")
    public AjaxResult remove(@ApiParam("主键串")
                                   @NotEmpty(message = "主键不能为空")
                                   @PathVariable Long[] participantsIds) {
        return toAjax(iActActivityParticipantsService.deleteWithValidByIds(Arrays.asList(participantsIds), true) ? 1 : 0);
    }
}
