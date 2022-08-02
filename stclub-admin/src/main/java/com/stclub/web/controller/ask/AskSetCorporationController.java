package com.stclub.web.controller.ask;

import com.stclub.ask.domain.AskSetCorporation;
import com.stclub.ask.domain.bo.AskSetCorporationBo;
import com.stclub.ask.domain.vo.AskSetCorporationVo;
import com.stclub.ask.service.IAskSetCorporationService;
import com.stclub.club.domain.CluCorporation;
import com.stclub.club.service.ICluCorporationService;
import com.stclub.common.annotation.Log;
import com.stclub.common.annotation.RepeatSubmit;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.core.page.TableDataInfo;
import com.stclub.common.enums.BusinessType;
import com.stclub.common.utils.DateUtils;
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
import java.util.Date;
import java.util.List;

/**
 * 成员创建社团申请管理Controller
 *
 * @author luo
 * @date 2022-03-09
 */
@Validated
@Api(value = "成员创建社团申请管理控制器", tags = {"成员创建社团申请管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ask/setCorporation")
public class AskSetCorporationController extends BaseController {

    private final IAskSetCorporationService iAskSetCorporationService;
    private final ICluCorporationService iCluCorporationService;

/**
 * 查询成员创建社团申请管理列表
 */
@ApiOperation("查询成员创建社团申请管理列表")
@PreAuthorize("@ss.hasPermi('ask:setCorporation:list')")
@GetMapping("/list")
    public TableDataInfo list( AskSetCorporationBo bo) {
        startPage();
        List<AskSetCorporationVo> askSetCorporationList = iAskSetCorporationService.getAskSetCorporationList(bo);
        return getDataTable(askSetCorporationList);
    }

    /**
     * 导出成员创建社团申请管理列表
     */
    @ApiOperation("导出成员创建社团申请管理列表")
    @PreAuthorize("@ss.hasPermi('ask:setCorporation:export')")
    @Log(title = "成员创建社团申请管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export( AskSetCorporationBo bo, HttpServletResponse response) {
        List<AskSetCorporationVo> list = iAskSetCorporationService.getAskSetCorporationList(bo);
        ExcelUtil<AskSetCorporationVo> util = new ExcelUtil<AskSetCorporationVo>(AskSetCorporationVo.class);
        util.exportExcel(response, list, "成员创建社团申请管理");
    }

    /**
     * 获取成员创建社团申请管理详细信息
     */
    @ApiOperation("获取成员创建社团申请管理详细信息")
    @PreAuthorize("@ss.hasPermi('ask:setCorporation:query')")
    @GetMapping("/{setCorporationId}")
    public AjaxResult getInfo(@ApiParam("主键")
                              @NotNull(message = "主键不能为空")
                              @PathVariable("setCorporationId") Long setCorporationId) {
        return AjaxResult.success(iAskSetCorporationService.getById(setCorporationId));
    }

    /**
     * 新增成员创建社团申请管理
     */
    @ApiOperation("新增成员创建社团申请管理")
    @PreAuthorize("@ss.hasPermi('ask:setCorporation:add')")
    @Log(title = "成员创建社团申请管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add( @RequestBody AskSetCorporationBo bo) {
        return toAjax(iAskSetCorporationService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改成员创建社团申请管理
     */
    @ApiOperation("修改成员创建社团申请管理")
    @PreAuthorize("@ss.hasPermi('ask:setCorporation:edit')")
    @Log(title = "成员创建社团申请管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit( @RequestBody AskSetCorporationBo bo) {
        AskSetCorporationBo bo1 = bo;
        return toAjax(iAskSetCorporationService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除成员创建社团申请管理
     */
    @ApiOperation("删除成员创建社团申请管理")
    @PreAuthorize("@ss.hasPermi('ask:setCorporation:remove')")
    @Log(title = "成员创建社团申请管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{setCorporationIds}")
    public AjaxResult remove(@ApiParam("主键串")
                             @NotEmpty(message = "主键不能为空")
                             @PathVariable Long[] setCorporationIds) {
        return toAjax(iAskSetCorporationService.deleteWithValidByIds(Arrays.asList(setCorporationIds), true) ? 1 : 0);
    }

    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody AskSetCorporation askSetCorporation){
        boolean b = iAskSetCorporationService.updateById(askSetCorporation);
        if (askSetCorporation.getStatus()==1) {
            CluCorporation cluCorporation = new CluCorporation();
            cluCorporation.setCorporationCode("CUL"+ DateUtils.dateTimeNow());
            cluCorporation.setSlogan(askSetCorporation.getCorporationSlogan());
            cluCorporation.setCreateTime(new Date());
            cluCorporation.setTypeId(askSetCorporation.getCorporationTypeId());
            cluCorporation.setNumber((long) 0);
            cluCorporation.setCorporationName(askSetCorporation.getCorporationName());
            cluCorporation.setLogo(askSetCorporation.getLogo());
            iCluCorporationService.save(cluCorporation);
        }
        return toAjax(b);
    }
}
