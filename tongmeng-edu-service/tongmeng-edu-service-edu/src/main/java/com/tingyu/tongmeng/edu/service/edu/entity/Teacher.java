package com.tingyu.tongmeng.edu.service.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * ??ʦ
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_teacher")
@ApiModel(value="Teacher对象", description="??ʦ")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "??ʦID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "??ʦ????")
    private String name;

    @ApiModelProperty(value = "??ʦ?")
    private String intro;

    @ApiModelProperty(value = "??ʦ??????һ?仰˵????ʦ")
    private String career;

    @ApiModelProperty(value = "ͷ?Σ?1 ?߼???ʦ??2??ϯ??ʦ")
    private Integer level;

    @ApiModelProperty(value = "??ʦͷ?")
    private String avatar;

    @ApiModelProperty(value = "???")
    private Integer sort;

    @ApiModelProperty(value = "?߼?ɾ??; 1??ɾ????0 δɾ??")
    private Integer deleted;

    @ApiModelProperty(value = "????ʱ?")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty(value = "????ʱ?")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
