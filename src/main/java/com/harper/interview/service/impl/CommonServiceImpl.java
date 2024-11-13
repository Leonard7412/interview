package com.harper.interview.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harper.interview.entity.CategoryEntity;
import com.harper.interview.mapper.CommonMapper;
import com.harper.interview.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 目录层级
 * @author: liuhb_mios_ah
 * @date: 2023/12/8 15:57
 * @param:
 * @return:
 **/
@Slf4j
@Service
public class CommonServiceImpl extends ServiceImpl<CommonMapper, CategoryEntity>
        implements CommonService {
    @Resource
    private CommonMapper commonMapper;

    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查询出所有分类
        List<CategoryEntity> entities = commonMapper.selectList(null);

        // 递归查询子菜单回填到结果
        return entities.stream()
                .filter(e -> e.getParentCid() == 0)
                .peek((menu) -> menu.setChildren(getChildrens(menu, entities)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

    // 递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {

        return all.stream().filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId())).peek(categoryEntity -> {
            // 找到子菜单(递归)
            categoryEntity.setChildren(getChildrens(categoryEntity, all));
        }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort()))).collect(Collectors.toList());

    }
}
