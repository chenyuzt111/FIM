package com.benewake.saleordersystem.service;

import com.benewake.saleordersystem.entity.ViewCol;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 */
public interface ViewColService {

    /**
     * 根据视图id获取视图对应的列信息
     *
     * 全部订单列表  tableid = 1  我的-viewid=0 全部-viewid=-1
     * 订单状态列表  tableid = 2  我的-viewid=0 po/pr/yg/yc/xd-viewid=-1
     * 客户类型列表  tableid = 3  我的-viewid=0 ND/YD/RC/DLS/XZ/LS-viewid=-1
     * 产品类型列表  tableid = 4  我的-viewid=0 已有标品/已有定制/新增软件定制/新增原材料定制/新增原材料+软件定制-viewid=-1
     * 订单转换列表  tableid = 5  我的-viewid=0 现有询单/现有预测/删除询单/删除预测/XD已变PR询单/YC已变PR询单/XD已变PO询单/YC已变PO询单=-1
     * 订单交付列表  tableid = 6  我的-viewid=0 已签收-viewid=-1 已发货客户未签收-viewid=-2 未发货-viewid=-3
     * @param viewId
     * @param isAdmin 是否是管理员
     * @return
     */
    List<Map<String,Object>> getCols(Long tableID,Long viewId, boolean isAdmin);

    /**
     * 保存新增方案列信息
     * @param cols
     * @return
     */
    int saveCols(List<ViewCol> cols);

    int deleteCols(Long viewId);
}
