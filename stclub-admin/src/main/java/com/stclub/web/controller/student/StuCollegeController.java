package com.stclub.web.controller.student;

import java.util.List;
import java.util.Arrays;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import com.stclub.student.domain.vo.StuCollegeVo;
import com.stclub.student.domain.StuCollege;
import com.stclub.student.domain.bo.StuCollegeBo;
import com.stclub.student.service.IStuCollegeService;
    import com.stclub.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 学院管理Controller
 *
 * @author luo
 * @date 2022-03-08
 */
@Validated
@Api(value = "学院管理控制器", tags = {"学院管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/student/college")
public class StuCollegeController extends BaseController {

    private final IStuCollegeService iStuCollegeService;

/**
 * 查询学院管理列表
 */
@ApiOperation("查询学院管理列表")
@PreAuthorize("@ss.hasPermi('student:college:list')")
@GetMapping("/list")
    public TableDataInfo list( StuCollegeBo bo) {
        startPage();
        List<StuCollege> list = iStuCollegeService.list(new LambdaQueryWrapper<StuCollege>().like(bo.getCollegeName()!=null && bo.getCollegeName()!="",StuCollege::getCollegeName,bo.getCollegeName()));
        return getDataTable(list);
    }

    /**
     * 导出学院管理列表
     */
    @ApiOperation("导出学院管理列表")
    @PreAuthorize("@ss.hasPermi('student:college:export')")
    @Log(title = "学院管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( StuCollegeBo bo, HttpServletResponse response) {
        List<StuCollegeVo> list = iStuCollegeService.listVo();
        ExcelUtil<StuCollegeVo> util = new ExcelUtil<StuCollegeVo>(StuCollegeVo.class);
        util.exportExcel(response, list, "学院管理");
    }

    /**
     * 获取学院管理详细信息
     */
    @ApiOperation("获取学院管理详细信息")
    @PreAuthorize("@ss.hasPermi('student:college:query')")
    @GetMapping("/{collegeId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("collegeId") Long collegeId) {
        return AjaxResult.success(iStuCollegeService.getById(collegeId));
    }

    /**
     * 新增学院管理
     */
    @ApiOperation("新增学院管理")
    @PreAuthorize("@ss.hasPermi('student:college:add')")
    @Log(title = "学院管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody StuCollegeBo bo) {

        return toAjax(iStuCollegeService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改学院管理
     */
    @ApiOperation("修改学院管理")
    @PreAuthorize("@ss.hasPermi('student:college:edit')")
    @Log(title = "学院管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody StuCollegeBo bo) {
        return toAjax(iStuCollegeService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除学院管理
     */
    @ApiOperation("删除学院管理")
    @PreAuthorize("@ss.hasPermi('student:college:remove')")
    @Log(title = "学院管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{collegeIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] collegeIds) {
        return toAjax(iStuCollegeService.deleteWithValidByIds(Arrays.asList(collegeIds), true) ? 1 : 0);
    }
}
