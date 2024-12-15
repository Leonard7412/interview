package com.harper.interview.service.impl;

import com.harper.interview.entity.TestEntity;
import com.harper.interview.service.ITestService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName：TestService
 * @Author: leo.harper
 * @Date: 2024/12/15 14:51
 * @Description: 必须描述类做什么事情, 实现什么功能
 */
@Service
public class TestService implements ITestService {
    @Override
    public void saveBatch(List<TestEntity> ts) {

    }

    @Override
    public List<TestEntity> list() {
        return null;
    }
}
