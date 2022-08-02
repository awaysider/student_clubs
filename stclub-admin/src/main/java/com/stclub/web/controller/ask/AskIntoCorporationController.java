package com.stclub.web.controller.ask;

import com.stclub.ask.domain.AskIntoCorporation;
import com.stclub.ask.domain.bo.AskIntoCorporationBo;
import com.stclub.ask.domain.vo.AskIntoCorporationVo;
import com.stclub.ask.service.IAskIntoCorporationService;
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
import java.util.Date;
import java.util.List;

/**
 * 用户入社申请管理Controller
 *
 * @author luo
 * @date 2022-03-09
 */
@Validated
@Api(value = "用户入社申请管理控制器", tags = {"用户入社申请管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ask/intoCorporation")
public class AskIntoCorporationController extends BaseController {

    private final IAskIntoCorporationService iAskIntoCorporationService;

    private final IStuUserIdentityService iStuUserIdentityService;

/**
 * 查询用户入社申请管理列表
 */
@ApiOperation("查询用户入社申请管理列表")
@PreAuthorize("@ss.hasPermi('ask:intoCorporation:list')")
@GetMapping("/list")
    public TableDataInfo list( AskIntoCorporationBo bo) {
        startPage();
    List<AskIntoCorporationVo> askIntoCorporationList = iAskIntoCorporationService.getAskIntoCorporationList(bo);
        return getDataTable(askIntoCorporationList);
    }

    /**
     * 导出用户入社申请管理列表
     */
    @ApiOperation("导出用户入社申请管理列表")
    @PreAuthorize("@ss.hasPermi('ask:intoCorporation:export')")
    @Log(title = "用户入社申请管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( AskIntoCorporationBo bo, HttpServletResponse response) {
        List<AskIntoCorporationVo> list = iAskIntoCorporationService.getAskIntoCorporationList(bo);
        ExcelUtil<AskIntoCorporationVo> util = new ExcelUtil<AskIntoCorporationVo>(AskIntoCorporationVo.class);
        util.exportExcel(response, list, "用户入社申请管理");
    }

    /**
     * 获取用户入社申请管理详细信息
     */
    @ApiOperation("获取用户入社申请管理详细信息")
    @PreAuthorize("@ss.hasPermi('ask:intoCorporation:query')")
    @GetMapping("/{intoCorporationId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("intoCorporationId") Long intoCorporationId) {
        return AjaxResult.success(iAskIntoCorporationService.getById(intoCorporationId));
    }

    /**
     * 新增用户入社申请管理
     */
    @ApiOperation("新增用户入社申请管理")
    @PreAuthorize("@ss.hasPermi('ask:intoCorporation:add')")
    @Log(title = "用户入社申请管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody AskIntoCorporationBo bo) {
        return toAjax(iAskIntoCorporationService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改用户入社申请管理
     */
    @ApiOperation("修改用户入社申请管理")
    @PreAuthorize("@ss.hasPermi('ask:intoCorporation:edit')")
    @Log(title = "用户入社申请管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody AskIntoCorporationBo bo) {
        return toAjax(iAskIntoCorporationService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除用户入社申请管理
     */
    @ApiOperation("删除用户入社申请管理")
    @PreAuthorize("@ss.hasPermi('ask:intoCorporation:remove')")
    @Log(title = "用户入社申请管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{intoCorporationIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] intoCorporationIds) {
        return toAjax(iAskIntoCorporationService.deleteWithValidByIds(Arrays.asList(intoCorporationIds), true) ? 1 : 0);
    }

    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody AskIntoCorporation askIntoCorporation){
        boolean b = iAskIntoCorporationService.updateById(askIntoCorporation);
        if(askIntoCorporation.getStatus()==1) {
            StuUserIdentity stuUserIdentity = new StuUserIdentity();
            stuUserIdentity.setUserId(askIntoCorporation.getUserId());
            stuUserIdentity.setCorporationId(askIntoCorporation.getCorporationId());
            stuUserIdentity.setUserStatus((long) 1);
            stuUserIdentity.setStatus((long) 0);
            stuUserIdentity.setJoinTime(new Date());
            iStuUserIdentityService.save(stuUserIdentity);
        }
        return toAjax(b);
    }
}
