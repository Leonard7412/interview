package com.harper.interview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题库题目
 * @TableName harper_question_relation
 */
@TableName(value ="harper_question_relation")
@Data
public class HarperQuestionRelation implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 题库 id
     */
    @TableField(value = "questionBankId")
    private Long questionBankId;

    /**
     * 题目 id
     */
    @TableField(value = "questionId")
    private Long questionId;

    /**
     * 创建用户 id
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 题目顺序（题号）
     */
    @TableField(value = "questionOrder")
    private Integer questionOrder;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
