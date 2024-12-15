package com.harper.interview.service;

import com.harper.interview.entity.TestEntity;

import java.util.List;

/**
 * @ClassName：ITestService
 * @Author: leo.harper
 * @Date: 2024/12/15 14:52
 * @Description: 必须描述类做什么事情, 实现什么功能
 */
public interface ITestService {
    void saveBatch(List<TestEntity> ts);

    List<TestEntity> list();
}
