package com.stclub.student.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 学院管理业务对象 stu_college
 *
 * @author luo
 * @date 2022-03-08
 */

@Data
@ApiModel("学院管理业务对象")
public class StuCollegeBo {

    /**
     * 学院主键id
     */
    @ApiModelProperty(value = "学院主键id")
    private Long collegeId;

    /**
     * 学院名
     */
    @ApiModelProperty(value = "学院名")
    private String collegeName;


}
