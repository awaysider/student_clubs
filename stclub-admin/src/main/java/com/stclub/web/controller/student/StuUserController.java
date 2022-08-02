package com.stclub.web.controller.student;

import java.util.List;
import java.util.Arrays;

import com.github.pagehelper.PageHelper;
import com.stclub.common.core.domain.entity.SysUser;
import com.stclub.common.utils.SecurityUtils;
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
import com.stclub.student.domain.vo.StuUserVo;
import com.stclub.student.domain.StuUser;
import com.stclub.student.domain.bo.StuUserBo;
import com.stclub.student.service.IStuUserService;
    import com.stclub.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户信息Controller
 *
 * @author luo
 * @date 2022-03-08
 */
@Validated
@Api(value = "用户信息控制器", tags = {"用户信息管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/student/user")
public class StuUserController extends BaseController {

    private final IStuUserService iStuUserService;

/**
 * 查询用户信息列表
 */
@ApiOperation("查询用户信息列表")
@PreAuthorize("@ss.hasPermi('student:user:list')")
@GetMapping("/list")
    public TableDataInfo list( StuUserBo bo) {
        startPage();
        List<StuUserVo> list = iStuUserService.getStuUserList(bo);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @ApiOperation("导出用户信息列表")
    @PreAuthorize("@ss.hasPermi('student:user:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( StuUserBo bo, HttpServletResponse response) {
        List<StuUserVo> list = iStuUserService.getStuUserList(bo);
        ExcelUtil<StuUserVo> util = new ExcelUtil<StuUserVo>(StuUserVo.class);
        util.exportExcel(response, list, "用户信息");
    }

    /**
     * 获取用户信息详细信息
     */
    @ApiOperation("获取用户信息详细信息")
    @PreAuthorize("@ss.hasPermi('student:user:query')")
    @GetMapping("/{userId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("userId") Long userId) {
        return AjaxResult.success(iStuUserService.getById(userId));
    }

    /**
     * 新增用户信息
     */
    @ApiOperation("新增用户信息")
    @PreAuthorize("@ss.hasPermi('student:user:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody StuUser stuUser) {
        return toAjax(iStuUserService.save(stuUser) ? 1 : 0);
    }

    /**
     * 修改用户信息
     */
    @ApiOperation("修改用户信息")
    @PreAuthorize("@ss.hasPermi('student:user:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody StuUserBo bo) {
        bo.setPassword(SecurityUtils.encryptPassword(bo.getPassword()));
        return toAjax(iStuUserService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除用户信息
     */
    @ApiOperation("删除用户信息")
    @PreAuthorize("@ss.hasPermi('student:user:remove')")
    @Log(title = "用户信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] userIds) {
        return toAjax(iStuUserService.deleteWithValidByIds(Arrays.asList(userIds), true) ? 1 : 0);
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('student:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody StuUser stuUser)
    {
        return toAjax(iStuUserService.updateById(stuUser));
    }
}
