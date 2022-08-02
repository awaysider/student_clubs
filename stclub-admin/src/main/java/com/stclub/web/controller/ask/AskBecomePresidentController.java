package com.stclub.web.controller.ask;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stclub.ask.domain.AskBecomePresident;
import com.stclub.ask.domain.bo.AskBecomePresidentBo;
import com.stclub.ask.domain.vo.AskBecomePresidentVo;
import com.stclub.ask.service.IAskBecomePresidentService;
import com.stclub.common.annotation.Log;
import com.stclub.common.annotation.RepeatSubmit;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.core.page.TableDataInfo;
import com.stclub.common.enums.BusinessType;
import com.stclub.common.utils.poi.ExcelUtil;
import com.stclub.student.domain.StuUserIdentity;
import com.stclub.student.service.IStuUserIdentityService;
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
 * 成员成为社长申请管理Controller
 *
 * @author luo
 * @date 2022-03-09
 */
@Validated
@Api(value = "成员成为社长申请管理控制器", tags = {"成员成为社长申请管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ask/becomePresident")
public class AskBecomePresidentController extends BaseController {

    private final IAskBecomePresidentService iAskBecomePresidentService;
    private final IStuUserIdentityService iStuUserIdentityService;
/**
 * 查询成员成为社长申请管理列表
 */
@ApiOperation("查询成员成为社长申请管理列表")
@PreAuthorize("@ss.hasPermi('ask:becomePresident:list')")
@GetMapping("/list")
    public TableDataInfo list( AskBecomePresidentBo bo) {
        startPage();
        List<AskBecomePresidentVo> list = iAskBecomePresidentService.getAskBecomePresidentList(bo);
        return getDataTable(list);
    }

    /**
     * 导出成员成为社长申请管理列表
     */
    @ApiOperation("导出成员成为社长申请管理列表")
    @PreAuthorize("@ss.hasPermi('ask:becomePresident:export')")
    @Log(title = "成员成为社长申请管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( AskBecomePresidentBo bo, HttpServletResponse response) {
        List<AskBecomePresidentVo> list = iAskBecomePresidentService.getAskBecomePresidentList(bo);
        ExcelUtil<AskBecomePresidentVo> util = new ExcelUtil<AskBecomePresidentVo>(AskBecomePresidentVo.class);
        util.exportExcel(response, list, "成员成为社长申请管理");
    }

    /**
     * 获取成员成为社长申请管理详细信息
     */
    @ApiOperation("获取成员成为社长申请管理详细信息")
    @PreAuthorize("@ss.hasPermi('ask:becomePresident:query')")
    @GetMapping("/{presidentId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("presidentId") Long presidentId) {
        return AjaxResult.success(iAskBecomePresidentService.getById(presidentId));
    }

    /**
     * 新增成员成为社长申请管理
     */
    @ApiOperation("新增成员成为社长申请管理")
    @PreAuthorize("@ss.hasPermi('ask:becomePresident:add')")
    @Log(title = "成员成为社长申请管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody AskBecomePresidentBo bo) {
        return toAjax(iAskBecomePresidentService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改成员成为社长申请管理
     */
    @ApiOperation("修改成员成为社长申请管理")
    @PreAuthorize("@ss.hasPermi('ask:becomePresident:edit')")
    @Log(title = "成员成为社长申请管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody AskBecomePresidentBo bo) {
        return toAjax(iAskBecomePresidentService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除成员成为社长申请管理
     */
    @ApiOperation("删除成员成为社长申请管理")
    @PreAuthorize("@ss.hasPermi('ask:becomePresident:remove')")
    @Log(title = "成员成为社长申请管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{presidentIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] presidentIds) {
        return toAjax(iAskBecomePresidentService.deleteWithValidByIds(Arrays.asList(presidentIds), true) ? 1 : 0);
    }

    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody AskBecomePresident askBecomePresident){
        boolean b = iAskBecomePresidentService.updateById(askBecomePresident);
        if(askBecomePresident.getStatus()==1) {
            StuUserIdentity stuUserIdentityOriginal = iStuUserIdentityService.getOne(new LambdaQueryWrapper<StuUserIdentity>().eq(StuUserIdentity::getCorporationId, askBecomePresident.getCorporationId()).eq(StuUserIdentity::getUserId, askBecomePresident.getOriginalUserId()));
            stuUserIdentityOriginal.setUserStatus((long) 1);
            StuUserIdentity stuUserIdentity = iStuUserIdentityService.getOne(new LambdaQueryWrapper<StuUserIdentity>().eq(StuUserIdentity::getCorporationId, askBecomePresident.getCorporationId()).eq(StuUserIdentity::getUserId, askBecomePresident.getUserId()));
            stuUserIdentity.setUserStatus((long) 0);
            iStuUserIdentityService.updateById(stuUserIdentityOriginal);
            iStuUserIdentityService.updateById(stuUserIdentity);
        }
        return toAjax(b);

    }
}
