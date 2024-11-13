package com.harper.interview.dto.harperQuestionRelation;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 编辑题库题目关联请求
 *
 */
@Data
public class HarperQuestionRelationEditRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}
