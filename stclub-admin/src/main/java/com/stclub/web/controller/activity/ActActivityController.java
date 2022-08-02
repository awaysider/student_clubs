package com.stclub.web.controller.activity;

import java.util.List;
import java.util.Arrays;

import cn.hutool.core.bean.BeanUtil;
import com.stclub.activity.domain.ActActivity;
import com.stclub.common.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

import org.apache.poi.ss.usermodel.DateUtil;
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
import com.stclub.activity.domain.vo.ActActivityVo;
import com.stclub.activity.domain.bo.ActActivityBo;
import com.stclub.activity.service.IActActivityService;
    import com.stclub.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 活动信息管理Controller
 *
 * @author luo
 * @date 2022-03-08
 */
@Validated
@Api(value = "活动信息管理控制器", tags = {"活动信息管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/activity/activity")
public class ActActivityController extends BaseController {

    private final IActActivityService iActActivityService;

    /**
     * 查询活动信息管理列表
     */
    @ApiOperation("查询活动信息管理列表")
    @PreAuthorize("@ss.hasPermi('activity:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActActivityBo bo) {
        startPage();
        List<ActActivityVo> list = iActActivityService.getList(bo);
        return getDataTable(list);
    }

    /**
     * 导出活动信息管理列表
     */
    @ApiOperation("导出活动信息管理列表")
    @PreAuthorize("@ss.hasPermi('activity:activity:export')")
    @Log(title = "活动信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated ActActivityBo bo, HttpServletResponse response) {
        List<ActActivityVo> list = iActActivityService.getList(bo);
        ExcelUtil<ActActivityVo> util = new ExcelUtil<ActActivityVo>(ActActivityVo.class);
        util.exportExcel(response, list, "活动信息管理");
    }

    /**
     * 获取活动信息管理详细信息
     */
    @ApiOperation("获取活动信息管理详细信息")
    @PreAuthorize("@ss.hasPermi('activity:activity:query')")
    @GetMapping("/{actId}")
    public AjaxResult getInfo(@ApiParam("主键")
                                              @NotNull(message = "主键不能为空")
                                              @PathVariable("actId") Long actId) {
        return AjaxResult.success(iActActivityService.getById(actId));
    }

    /**
     * 新增活动信息管理
     */
    @ApiOperation("新增活动信息管理")
    @PreAuthorize("@ss.hasPermi('activity:activity:add')")
    @Log(title = "活动信息管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@RequestBody ActActivityBo bo) {
        bo.setActivityCode("ACT"+DateUtils.dateTimeNow());
        return toAjax(iActActivityService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改活动信息管理
     */
    @ApiOperation("修改活动信息管理")
    @PreAuthorize("@ss.hasPermi('activity:activity:edit')")
    @Log(title = "活动信息管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody ActActivity actActivity) {
        return toAjax(iActActivityService.updateById(actActivity) ? 1 : 0);
    }

    /**
     * 删除活动信息管理
     */
    @ApiOperation("删除活动信息管理")
    @PreAuthorize("@ss.hasPermi('activity:activity:remove')")
    @Log(title = "活动信息管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public AjaxResult remove(@ApiParam("主键串")
                                   @NotEmpty(message = "主键不能为空")
                                   @PathVariable Long[] activityIds) {
        return toAjax(iActActivityService.deleteWithValidByIds(Arrays.asList(activityIds), true) ? 1 : 0);
    }
}
