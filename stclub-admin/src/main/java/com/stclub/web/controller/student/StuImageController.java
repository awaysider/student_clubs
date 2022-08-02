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
import com.stclub.student.domain.vo.StuImageVo;
import com.stclub.student.domain.StuImage;
import com.stclub.student.domain.bo.StuImageBo;
import com.stclub.student.service.IStuImageService;
    import com.stclub.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 首页轮番图Controller
 *
 * @author ruoyi
 * @date 2022-04-12
 */
@Validated
@Api(value = "首页轮番图控制器", tags = {"首页轮番图管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/student/image")
public class StuImageController extends BaseController {

    private final IStuImageService iStuImageService;

/**
 * 查询首页轮番图列表
 */
@ApiOperation("查询首页轮番图列表")
@PreAuthorize("@ss.hasPermi('student:image:list')")
@GetMapping("/list")
    public TableDataInfo list( StuImageBo bo) {
        startPage();
        List<StuImage> list = iStuImageService.list();
        return getDataTable(list);
    }

    /**
     * 导出首页轮番图列表
     */
    @ApiOperation("导出首页轮番图列表")
    @PreAuthorize("@ss.hasPermi('student:image:export')")
    @Log(title = "首页轮番图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( StuImageBo bo, HttpServletResponse response) {
        List<StuImageVo> list = iStuImageService.listVo();
        ExcelUtil<StuImageVo> util = new ExcelUtil<StuImageVo>(StuImageVo.class);
        util.exportExcel(response, list, "首页轮番图");
    }

    /**
     * 获取首页轮番图详细信息
     */
    @ApiOperation("获取首页轮番图详细信息")
    @PreAuthorize("@ss.hasPermi('student:image:query')")
    @GetMapping("/{imageId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("imageId") Long imageId) {
        return AjaxResult.success(iStuImageService.getById(imageId));
    }

    /**
     * 新增首页轮番图
     */
    @ApiOperation("新增首页轮番图")
    @PreAuthorize("@ss.hasPermi('student:image:add')")
    @Log(title = "首页轮番图", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody StuImageBo bo) {
        return toAjax(iStuImageService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改首页轮番图
     */
    @ApiOperation("修改首页轮番图")
    @PreAuthorize("@ss.hasPermi('student:image:edit')")
    @Log(title = "首页轮番图", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody StuImageBo bo) {
        return toAjax(iStuImageService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除首页轮番图
     */
    @ApiOperation("删除首页轮番图")
    @PreAuthorize("@ss.hasPermi('student:image:remove')")
    @Log(title = "首页轮番图" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{imageIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] imageIds) {
        return toAjax(iStuImageService.deleteWithValidByIds(Arrays.asList(imageIds), true) ? 1 : 0);
    }
}
