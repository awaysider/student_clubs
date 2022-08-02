package com.stclub.web.api.common;

import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.utils.MinioClientUtils;
import com.stclub.student.domain.StuCollege;
import com.stclub.student.service.IStuCollegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * 公共接口模块
 * @author ljx
 * @Date 2022/3/31 1:04
 **/
@Api(value = "公共接口模块", tags = {"公共接口模块"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/common")
public class CommonApiController {
    private final IStuCollegeService iStuCollegeService;


    private final MinioClientUtils minioUtil;

    @ApiOperation(value = "获取学院信息列表",notes = "获取学院信息列表")
    @GetMapping("/getCollegeList")
    public AjaxResult getCollegeList(){
        List<StuCollege> list = iStuCollegeService.list();
        return AjaxResult.success(list);
    }

    /**
     * 通用上传请求（单个）
     */
    @ApiOperation(value = "上传文件接口")
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        System.out.println("ddd");
        try {
            if (!minioUtil.bucketExists("club")) {
                minioUtil.makeBucket("club");
            }

            String fileName = file.getOriginalFilename();
            String newName = "image/" + UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();
            minioUtil.putObject("club", file, newName);
            inputStream.close();

            String url = minioUtil.getObjectUrl("club", newName);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }


}
