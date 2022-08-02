package com.stclub.student.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
 * 学院管理对象 stu_college
 *
 * @author luo
 * @date 2022-03-08
 */
@Data
@Accessors(chain = true)
@TableName("stu_college")
public class StuCollege implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 学院主键id
     */
        @TableId(value = "college_id")
    private Long collegeId;
    /**
     * 学院名
     */
    private String collegeName;

}
