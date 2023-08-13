package com.benewake.saleordersystem.service;

import com.benewake.saleordersystem.entity.Item;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 */
public interface ItemService {

    /**
     * 根据物料编码模糊查询物料
     *
     * @param itemCode
     * @return
     */
    List<Map<String, Object>> itemCodeLikeList(String itemCode);

    /**
     * 根据id判断物料是否存在
     * @param itemId
     * @return
     */
    Item findItemById(Long itemId);

    /**
     * 根据物料编码查询物料
     * @param itemCode
     * @return
     */
    Item findItemByCode(String itemCode);

    /**
     * 将字符串对应转换为int
     * @param itemType
     * @return
     */
    int transferItemType(String itemType);

    /**
     * 模糊匹配物料名称
     * @param key
     * @return
     */
    List<Item> getItemNameList(String key);

    /**
     * 模糊匹配产品类型
     * @param itemType
     * @return
     */
    List<String> getItemTypeList(String itemType);
}
