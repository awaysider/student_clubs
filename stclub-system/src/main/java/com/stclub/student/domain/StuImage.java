package com.stclub.student.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
 * 首页轮番图对象 stu_image
 *
 * @author ruoyi
 * @date 2022-04-12
 */
@Data
@Accessors(chain = true)
@TableName("stu_image")
public class StuImage implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 图片Id
     */
        @TableId(value = "image_id")
    private Long imageId;
    /**
     * 图片地址
     */
    private String image;

}
