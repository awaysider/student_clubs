package com.stclub.web.controller.club;

import com.stclub.club.domain.CluCorporation;
import com.stclub.club.domain.bo.CluCorporationBo;
import com.stclub.club.domain.vo.CluCorporationVo;
import com.stclub.club.service.ICluCorporationService;
import com.stclub.common.annotation.Log;
import com.stclub.common.annotation.RepeatSubmit;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.core.page.TableDataInfo;
import com.stclub.common.enums.BusinessType;
import com.stclub.common.utils.DateUtils;
import com.stclub.common.utils.poi.ExcelUtil;
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
 * 社团Controller
 *
 * @author luo
 * @date 2022-03-09
 */
@Validated
@Api(value = "社团控制器", tags = {"社团管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/club/corporation")
public class CluCorporationController extends BaseController {

    private final ICluCorporationService iCluCorporationService;
    private final IStuUserIdentityService iStuUserIdentityService;

/**
 * 查询社团列表
 */
@ApiOperation("查询社团列表")
@PreAuthorize("@ss.hasPermi('club:corporation:list')")
@GetMapping("/list")
    public TableDataInfo list( CluCorporationBo bo) {
        startPage();
        List<CluCorporationVo> list = iCluCorporationService.getCorporationList(bo);
        return getDataTable(list);
    }

    /**
     * 导出社团列表
     */
    @ApiOperation("导出社团列表")
    @PreAuthorize("@ss.hasPermi('club:corporation:export')")
    @Log(title = "社团", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( CluCorporationBo bo, HttpServletResponse response) {
        List<CluCorporationVo> list = iCluCorporationService.getCorporationList(bo);
        ExcelUtil<CluCorporationVo> util = new ExcelUtil<CluCorporationVo>(CluCorporationVo.class);
        util.exportExcel(response, list, "社团");
    }

    /**
     * 获取社团详细信息
     */
    @ApiOperation("获取社团详细信息")
    @PreAuthorize("@ss.hasPermi('club:corporation:query')")
    @GetMapping("/{corporationId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("corporationId") Long corporationId) {
        return AjaxResult.success(iCluCorporationService.getById(corporationId));
    }

    /**
     * 新增社团
     */
    @ApiOperation("新增社团")
    @PreAuthorize("@ss.hasPermi('club:corporation:add')")
    @Log(title = "社团", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody CluCorporationBo bo) {
        bo.setCorporationCode("CUL"+ DateUtils.dateTimeNow());
        bo.setNumber((long )0);
        bo.setCreateTime(new Date());
        return toAjax(iCluCorporationService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改社团
     */
    @ApiOperation("修改社团")
    @PreAuthorize("@ss.hasPermi('club:corporation:edit')")
    @Log(title = "社团", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody CluCorporationBo bo) {
        return toAjax(iCluCorporationService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除社团
     */
    @ApiOperation("删除社团")
    @PreAuthorize("@ss.hasPermi('club:corporation:remove')")
    @Log(title = "社团" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{corporationIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] corporationIds) {
        return toAjax(iCluCorporationService.deleteWithValidByIds(Arrays.asList(corporationIds), true) ? 1 : 0);
    }

    @GetMapping("/getStuUserIdentityList/{corporationId}")
    public AjaxResult getStuUserIdentityList(
            @PathVariable Long corporationId
    ){
        return AjaxResult.success(iStuUserIdentityService.getStuUserIdentityListByCorporationId(corporationId));
    }
}
