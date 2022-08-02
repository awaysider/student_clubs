package com.stclub.web.controller.ask;

import com.stclub.ask.domain.AskIntoInterview;
import com.stclub.ask.domain.bo.AskIntoInterviewBo;
import com.stclub.ask.domain.vo.AskIntoInterviewVo;
import com.stclub.ask.service.IAskIntoInterviewService;
import com.stclub.common.annotation.Log;
import com.stclub.common.annotation.RepeatSubmit;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.core.page.TableDataInfo;
import com.stclub.common.enums.BusinessType;
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
import java.util.List;

/**
 * 入社面试邀请管理Controller
 *
 * @author luo
 * @date 2022-03-09
 */
@Validated
@Api(value = "入社面试邀请管理控制器", tags = {"入社面试邀请管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ask/intoInterview")
public class AskIntoInterviewController extends BaseController {

    private final IAskIntoInterviewService iAskIntoInterviewService;

/**
 * 查询入社面试邀请管理列表
 */
@ApiOperation("查询入社面试邀请管理列表")
@PreAuthorize("@ss.hasPermi('ask:intoInterview:list')")
@GetMapping("/list")
    public TableDataInfo list( AskIntoInterviewBo bo) {
        startPage();
    List<AskIntoInterviewVo> askIntoInterviewList = iAskIntoInterviewService.getAskIntoInterviewList(bo);
        return getDataTable(askIntoInterviewList);
    }

    /**
     * 导出入社面试邀请管理列表
     */
    @ApiOperation("导出入社面试邀请管理列表")
    @PreAuthorize("@ss.hasPermi('ask:intoInterview:export')")
    @Log(title = "入社面试邀请管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( AskIntoInterviewBo bo, HttpServletResponse response) {
        List<AskIntoInterviewVo> list = iAskIntoInterviewService.getAskIntoInterviewList(bo);
        ExcelUtil<AskIntoInterviewVo> util = new ExcelUtil<AskIntoInterviewVo>(AskIntoInterviewVo.class);
        util.exportExcel(response, list, "入社面试邀请管理");
    }

    /**
     * 获取入社面试邀请管理详细信息
     */
    @ApiOperation("获取入社面试邀请管理详细信息")
    @PreAuthorize("@ss.hasPermi('ask:intoInterview:query')")
    @GetMapping("/{intoInterviewId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("intoInterviewId") Long intoInterviewId) {
        return AjaxResult.success(iAskIntoInterviewService.getById(intoInterviewId));
    }

    /**
     * 新增入社面试邀请管理
     */
    @ApiOperation("新增入社面试邀请管理")
    @PreAuthorize("@ss.hasPermi('ask:intoInterview:add')")
    @Log(title = "入社面试邀请管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody AskIntoInterviewBo bo) {
        return toAjax(iAskIntoInterviewService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改入社面试邀请管理
     */
    @ApiOperation("修改入社面试邀请管理")
    @PreAuthorize("@ss.hasPermi('ask:intoInterview:edit')")
    @Log(title = "入社面试邀请管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody AskIntoInterviewBo bo) {
        return toAjax(iAskIntoInterviewService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除入社面试邀请管理
     */
    @ApiOperation("删除入社面试邀请管理")
    @PreAuthorize("@ss.hasPermi('ask:intoInterview:remove')")
    @Log(title = "入社面试邀请管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{intoInterviewIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] intoInterviewIds) {
        return toAjax(iAskIntoInterviewService.deleteWithValidByIds(Arrays.asList(intoInterviewIds), true) ? 1 : 0);
    }

    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody AskIntoInterview askIntoInterview){
        return toAjax(iAskIntoInterviewService.updateById(askIntoInterview));
    }
}
