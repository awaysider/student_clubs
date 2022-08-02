package com.stclub.web.controller.club;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stclub.club.domain.CluCorporationType;
import com.stclub.club.domain.bo.CluCorporationTypeBo;
import com.stclub.club.domain.vo.CluCorporationTypeVo;
import com.stclub.club.service.ICluCorporationTypeService;
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
 * 社团类型Controller
 *
 * @author ruoyi
 * @date 2022-03-09
 */
@Validated
@Api(value = "社团类型控制器", tags = {"社团类型管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/club/type")
public class CluCorporationTypeController extends BaseController {

    private final ICluCorporationTypeService iCluCorporationTypeService;

/**
 * 查询社团类型列表
 */
@ApiOperation("查询社团类型列表")
@PreAuthorize("@ss.hasPermi('club:type:list')")
@GetMapping("/list")
    public TableDataInfo list( CluCorporationTypeBo bo) {
        startPage();
        List<CluCorporationType> list = iCluCorporationTypeService.list(new LambdaQueryWrapper<CluCorporationType>().like(bo.getTypeName()!=null && bo.getTypeName()!="",CluCorporationType::getTypeName,bo.getTypeName()));
        return getDataTable(list);
    }

    /**
     * 导出社团类型列表
     */
    @ApiOperation("导出社团类型列表")
    @PreAuthorize("@ss.hasPermi('club:type:export')")
    @Log(title = "社团类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( CluCorporationTypeBo bo, HttpServletResponse response) {
        List<CluCorporationTypeVo> list = iCluCorporationTypeService.listVo();
        ExcelUtil<CluCorporationTypeVo> util = new ExcelUtil<CluCorporationTypeVo>(CluCorporationTypeVo.class);
        util.exportExcel(response, list, "社团类型");
    }

    /**
     * 获取社团类型详细信息
     */
    @ApiOperation("获取社团类型详细信息")
    @PreAuthorize("@ss.hasPermi('club:type:query')")
    @GetMapping("/{coporationTypeId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("coporationTypeId") Long coporationTypeId) {
        return AjaxResult.success(iCluCorporationTypeService.getById(coporationTypeId));
    }

    /**
     * 新增社团类型
     */
    @ApiOperation("新增社团类型")
    @PreAuthorize("@ss.hasPermi('club:type:add')")
    @Log(title = "社团类型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody CluCorporationTypeBo bo) {
        return toAjax(iCluCorporationTypeService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改社团类型
     */
    @ApiOperation("修改社团类型")
    @PreAuthorize("@ss.hasPermi('club:type:edit')")
    @Log(title = "社团类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody CluCorporationTypeBo bo) {
        return toAjax(iCluCorporationTypeService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除社团类型
     */
    @ApiOperation("删除社团类型")
    @PreAuthorize("@ss.hasPermi('club:type:remove')")
    @Log(title = "社团类型" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{coporationTypeIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] coporationTypeIds) {
        return toAjax(iCluCorporationTypeService.deleteWithValidByIds(Arrays.asList(coporationTypeIds), true) ? 1 : 0);
    }
}
