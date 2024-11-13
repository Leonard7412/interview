package com.harper.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harper.template.entity.CategoryEntity;

import java.util.List;

public interface CommonService extends IService<CategoryEntity> {
    List<CategoryEntity> listWithTree();
}
