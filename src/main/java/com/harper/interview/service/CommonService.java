package com.harper.interview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harper.interview.entity.CategoryEntity;

import java.util.List;

public interface CommonService extends IService<CategoryEntity> {
    List<CategoryEntity> listWithTree();
}
