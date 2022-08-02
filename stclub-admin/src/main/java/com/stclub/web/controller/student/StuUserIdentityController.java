package com.stclub.web.controller.student;

import java.util.List;
import java.util.Arrays;

import com.github.pagehelper.PageHelper;
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
import com.stclub.student.domain.vo.StuUserIdentityVo;
import com.stclub.student.domain.StuUserIdentity;
import com.stclub.student.domain.bo.StuUserIdentityBo;
import com.stclub.student.service.IStuUserIdentityService;
    import com.stclub.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户身份管理Controller
 *
 * @author luo
 * @date 2022-03-08
 */
@Validated
@Api(value = "用户身份管理控制器", tags = {"用户身份管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/student/userIdentity")
public class StuUserIdentityController extends BaseController {

    private final IStuUserIdentityService iStuUserIdentityService;

/**
 * 查询用户身份管理列表
 */
@ApiOperation("查询用户身份管理列表")
@PreAuthorize("@ss.hasPermi('student:userIdentity:list')")
@GetMapping("/list")
    public TableDataInfo list( StuUserIdentityBo bo) {
        startPage();
        List<StuUserIdentityVo> list = iStuUserIdentityService.getStuUserIdentityList(bo);
        return getDataTable(list);
    }

    /**
     * 导出用户身份管理列表
     */
    @ApiOperation("导出用户身份管理列表")
    @PreAuthorize("@ss.hasPermi('student:userIdentity:export')")
    @Log(title = "用户身份管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( StuUserIdentityBo bo, HttpServletResponse response) {
        List<StuUserIdentityVo> list = iStuUserIdentityService.getStuUserIdentityList(bo);
        ExcelUtil<StuUserIdentityVo> util = new ExcelUtil<StuUserIdentityVo>(StuUserIdentityVo.class);
        util.exportExcel(response, list, "用户身份管理");
    }

    /**
     * 获取用户身份管理详细信息
     */
    @ApiOperation("获取用户身份管理详细信息")
    @PreAuthorize("@ss.hasPermi('student:userIdentity:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("id") Long id) {
        return AjaxResult.success(iStuUserIdentityService.getById(id));
    }

    /**
     * 新增用户身份管理
     */
    @ApiOperation("新增用户身份管理")
    @PreAuthorize("@ss.hasPermi('student:userIdentity:add')")
    @Log(title = "用户身份管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody StuUserIdentityBo bo) {
        return toAjax(iStuUserIdentityService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改用户身份管理
     */
    @ApiOperation("修改用户身份管理")
    @PreAuthorize("@ss.hasPermi('student:userIdentity:edit')")
    @Log(title = "用户身份管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody StuUserIdentityBo bo) {
        return toAjax(iStuUserIdentityService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除用户身份管理
     */
    @ApiOperation("删除用户身份管理")
    @PreAuthorize("@ss.hasPermi('student:userIdentity:remove')")
    @Log(title = "用户身份管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] ids) {
        return toAjax(iStuUserIdentityService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
