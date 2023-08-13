package com.benewake.saleordersystem.service;

import com.benewake.saleordersystem.entity.View;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月12 13:53
 * 描 述： TODO
 */
public interface ViewService {

    /**
     * 根据用户id和表id获取用户视图
     * @param userId
     * @param tableId
     * @return
     */
    List<View> getUserView(Long userId,Long tableId);

    /**
     * 保存用户新增方案
     * @param view
     * @return
     */
    int saveView(View view);

    /**
     * 获取所有列信息
     * @param tableId
     * @return
     */
    List<Map<String, Object>> getAllCols(Long tableId);

    /**
     * 更新视图信息
     * @param view
     * @return
     */
    int updateView(View view);

    /**
     * 判断视图名称是否已存在
     * @param tableId
     * @param id
     * @param viewName
     * @return
     */
    boolean isExist(Long tableId, Long id, String viewName);

    /**
     * 删除视图
     * @param viewId
     * @return
     */
    boolean deleteView(Long viewId);
}
