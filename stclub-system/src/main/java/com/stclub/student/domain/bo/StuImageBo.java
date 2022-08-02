package com.stclub.student.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 首页轮番图业务对象 stu_image
 *
 * @author ruoyi
 * @date 2022-04-12
 */

@Data
@ApiModel("首页轮番图业务对象")
public class StuImageBo {

    /**
     * 图片Id
     */
    @ApiModelProperty(value = "图片Id")
    private Long imageId;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String image;


}
